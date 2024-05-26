package board;

import units.Enemy;
import units.SpaceEntity;
import units.Void;


public class Board {
    // Author Semykopenko Ihor
    protected final int length;
    protected final int height;

    protected final SpaceEntity[][] matrix;

    public Board(int length, int height) {
        this.length = length;
        this.height = height;

        this.matrix = new SpaceEntity[length][height];

        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                matrix[x][y] = new Void();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("\n");

        for (int x = length - 1; x >= 0; x--) {
            for (int y = 0; y < height; y++) {
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
        return matrix[x][y] instanceof Void;
    }

    public SpaceEntity getPosition(int x, int y) throws PositionException {
        validateCoordinates(x, y);
        return matrix[x][y];
    }

    public void setPosition(int x, int y, SpaceEntity unit) throws PositionException {
        validateCoordinates(x, y);

        if (isVoidPosition(x, y)) {
            matrix[x][y] = unit;
            return;
        }

        String message = "Position (%d, %d) is already occupied.".formatted(x, y);
        throw new PositionException(message);

    }

    public void clearPosition(int x, int y) throws PositionException {
        validateCoordinates(x, y);
        matrix[x][y] = new Void();
    }

    public void moveUnit(int oldX, int oldY, int newX, int newY) throws PositionException {
        validateCoordinates(oldX, oldY);

        if (!isVoidPosition(oldX, oldY) && isVoidPosition(newX, newY)) {
            setPosition(newX, newY, getPosition(oldX, oldY));
            clearPosition(oldX, oldY);
        }
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public static void main(String[] args) throws PositionException {
        Board board = new Board(10, 40);
        System.out.println(board);

        board.setPosition(9, 0, new Enemy());
        System.out.println(board);

        board.moveUnit(9, 0, 0, 9);
        System.out.println(board);

    }
}
