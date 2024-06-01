package units;

import board.Board;
import board.PositionException;

import java.util.Arrays;


public abstract class SpaceUnit {

    private int x;
    private int y;
    protected final int size;
    protected final int[][] area;
    protected final Board board;

    protected SpaceUnit(int x, int y, int size, Board board) throws PositionException {
        // X and Y it will be center of object
        this.x = x;
        this.y = y;
        this.size = size;
        this.board = board;
        this.area = new int[(int) Math.pow(this.size * 2, 2)][2];

        setObjectOnBoard(genNewArea(x, y, size, board,this));
    }

    protected SpaceUnit(int x, int y, Board board) throws PositionException {
        this(x, y, 1, board);
    }

    public void kill() {
        // Must be abstract and rewrite
    }

    public int[][] getArea() {
        return this.area;
    }

    private void setArea(int[][] generatedArea) {
        System.arraycopy(generatedArea, 0, this.area, 0, generatedArea.length);
    }

    protected void removeArea() throws PositionException {
        for (int[] oldCoordinates : this.area) {
            this.board.clearPosition(oldCoordinates[0], oldCoordinates[1]);
        }
        this.setArea(new int[][]{{}});
    }

    protected int[] getPosition() {
        return new int[]{this.x, this.y};
    }

    protected void setPosition(int x, int y) throws PositionException {
        // In case of fall. Save previous coordinates and points
        int[][] newArea = genNewArea(x, y, this.size, this.board, this);
        removeArea();
        setObjectOnBoard(newArea);
        this.x = x;
        this.y = y;
    }

    private int[][] genNewArea(int x, int y, int size, Board board, SpaceUnit unit) throws PositionException {
        // Generate a points of an object. bases of size
        int counter = 0;
        int range = size - 1;
        int[][] result = new int[(int) Math.pow(size * 2, 2)][2];

        for (int X = -range; X <= range; X++) {
            for (int Y = -range; Y <= range; Y++) {
                if (X + x == 0 && Y + y == 0) {
                    System.out.printf("unit %s %n", unit);
                }
                if (board.isVoidPosition(X + x, Y + y)) {
                    result[counter] = new int[]{X + x, Y + y};
                };
                counter++;
            }
        }
        System.out.print(Arrays.deepToString(result));
        return result;
    }

    private void setObjectOnBoard(int[][] newArea) throws PositionException {
        for (int[] coordinates : newArea) {
            this.board.setPosition(coordinates[0], coordinates[1], this);
        }
        setArea(newArea);
    }

    protected void moveLeft(int speed) throws PositionException {
        // Speed of an object - a square for a one frame

        int[] move = {-1, 0};

        int nextX = this.x + move[0] * speed;
        int nextY = this.y;

        setPosition(nextX, nextY);
    }

    protected void moveRight(int speed) throws PositionException {
        // Speed of an object - a square for a one frame

        int[] move = {1, 0};

        int nextX = this.x + move[0] * speed;
        int nextY = this.y;

        setPosition(nextX, nextY);
    }

    protected void moveUp(int speed) throws PositionException {
        // Speed of an object - a square for a one frame

        int[] move = {0, 1};

        int nextX = this.x;
        int nextY = this.y + move[1] * speed;

        setPosition(nextX, nextY);
    }

    protected void moveDown(int speed) throws PositionException {
        // Speed of an object - a square for a one frame

        int[] move = {0, -1};

        int nextX = this.x;
        int nextY = this.y + move[1] * speed;

        setPosition(nextX, nextY);
    }
}
