package board;

import units.SpaceUnit;
import units.Void;


public class Board {
    // Author Semykopenko Ihor
    protected final int length;
    protected final int height;

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

    public boolean isValidatePosition(int x, int y) {
        return (x >= 0 && x < length) && (y >= 0 && y < height);
    }

    protected void validateCoordinates(int x, int y) throws PositionException {
        if (!isValidatePosition(x, y)) {
            String message = "Invalid coordinates 0<=( %s )<%s 0<=( %s )<%s".formatted(x, length, y, height);
            throw new PositionException(message);
        }
    }

    public boolean isVoidPosition(int x, int y) throws PositionException {
        validateCoordinates(x, y);
        return matrix[x][y] instanceof Void || matrix[x][y] == null;
    }

    public boolean isVoidPosition(int x, int y, SpaceUnit unit) throws PositionException {
        for (int[] coordinates : unit.getAreaObject()) {
            if (x == coordinates[0] && y == coordinates[1]) {
                return true;
            }
        }
        return isVoidPosition(x, y);
    }

    public SpaceUnit getPosition(int x, int y) throws PositionException {
        validateCoordinates(x, y);
        return matrix[x][y];
    }

    public void setPosition(int x, int y, SpaceUnit unit) throws PositionException {
        validateCoordinates(x, y);

        if (isVoidPosition(x, y, unit)) {
            matrix[x][y] = unit;
            return;
        }

        String message = "Position (%d, %d) is already occupied.".formatted(x, y);
        throw new PositionException(message);

    }

    public void clearPosition(int x, int y) throws PositionException {
        validateCoordinates(x, y);
        matrix[x][y] = new Void(x, y, this);
    }

    public void moveUnit(int oldX, int oldY, int newX, int newY) throws PositionException {
        validateCoordinates(oldX, oldY);

        if (!isVoidPosition(oldX, oldY) && isVoidPosition(newX, newY)) {
            setPosition(newX, newY, getPosition(oldX, oldY));
            clearPosition(oldX, oldY);
        }
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
