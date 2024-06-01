package board;

import units.SpaceUnit;
import units.Void;

import java.util.Arrays;
import java.util.Iterator;


public class Board implements Iterable<SpaceUnit[]> {
    // Author Semykopenko Ihor
    private final int length;
    private final int height;

    protected final SpaceUnit[][] matrix;

    public Board(int length, int height) throws PositionException {
        this.length = length; // 1920
        this.height = height; // 1080

        this.matrix = new SpaceUnit[length][height];

        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                new Void(x, y, this);
            }
        }
    }

    @Override
    public Iterator<SpaceUnit[]> iterator() {
        return Arrays.stream(matrix).iterator();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("\n");

        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < length; x++) {
                result.append(matrix[x][y].toString());
            }
            result.append("\n");
        }

        return result.toString();
    }

    public void validateCoordinates(int x, int y) throws PositionException {
        if (!(x >= 0 && x < length) && (y >= 0 && y < height)) {
            String message = "Invalid coordinates 0<=( %s )<%s 0<=( %s )<%s".formatted(x, length, y, height);
            throw new PositionException(message);
        }
    }

    public boolean isVoidPosition(int x, int y) throws PositionException {
        validateCoordinates(x, y);
        return matrix[x][y] instanceof Void || matrix[x][y] == null;
    }

    public SpaceUnit getPosition(int x, int y) throws PositionException {
        validateCoordinates(x, y);
        return matrix[x][y];
    }

    public void setPosition(int x, int y, SpaceUnit unit) throws PositionException {
        validateCoordinates(x, y);
        matrix[x][y] = unit;
    }

    public void clearPosition(int x, int y) throws PositionException {
        validateCoordinates(x, y);
        matrix[x][y] = new Void(x, y, this);
    }

    public int getLength() {
        return this.length;
    }

    public int getHeight() {
        return this.height;
    }

    public static void main(String[] args) throws PositionException {
        Board board = new Board(15, 6);
        System.out.println(board);
    }
}