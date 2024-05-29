package units;

import board.Board;
import board.PositionException;


public abstract class SpaceUnit {

    private int x;
    private int y;
    protected final int size;
    protected final int rangeSize;
    protected final int[][] areaObject;
    protected final Board board;

    protected SpaceUnit(int x, int y, int size, Board board) throws PositionException {
        // X and Y it will be center of object
        this.x = x;
        this.y = y;
        this.size = size;
        this.board = board;
        this.rangeSize = this.size - 1;
        this.areaObject = new int[(int) Math.pow(rangeSize * 2 + 1, 2)][2];

        generateAreaOfObject();
        setupObjectOnBoard();
    }

    protected SpaceUnit(int x, int y, Board board) throws PositionException {
        this(x, y, 1, board);
    }

    public void kill() {

    }

    public int[][] getAreaObject() {
        return areaObject;
    }

    protected int[] getPosition() {
        return new int[]{this.x, this.y};
    }

    protected void setPosition(int x, int y) throws PositionException {
        // In case of fall. Save previous coordinates and points
        int tempX = this.x;
        int tempY = this.y;

        this.x = x;
        this.y = y;

        try {
            generateAreaOfObject();
            setObjectOnBoard();
        } catch (PositionException e) {
//            setPosition(tempX, tempY); // Backup
            throw new PositionException(e.toString());
        }
    }

    private void generateAreaOfObject() {
        // Generate a points of an object. bases of size
        int counter = 0;
        for (int innerX = -rangeSize; innerX <= rangeSize; innerX++) {
            for (int innerY = -rangeSize; innerY <= rangeSize; innerY++) {
                this.areaObject[counter] = new int[]{innerX + x, innerY + y};
                counter++;
            }
        }
    }

    private void setupObjectOnBoard() throws PositionException {
        // Setup points of an object on the board
        for (int i = 0; i < Math.pow(rangeSize * 2 + 1, 2); i++) {
            int[] coordinates = areaObject[i];
            this.board.setPosition(coordinates[0], coordinates[1], this);
        }
    }

    private void setObjectOnBoard() throws PositionException {
        for (int i = 0; i < Math.pow(rangeSize * 2 + 1, 2); i++) {
            int[] coordinates = areaObject[i];
//            this.board.m(coordinates[0], coordinates[1], this);
        }

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
