package units;

import board.Board;
import board.PositionException;

public class Defender extends SpaceUnit implements Runnable {

    public Defender(int x, int y, int size, Board board) throws PositionException {
        super(x, y, size, board);
    }

    @Override
    public String toString() {
        return "D";
    }

    @Override
    public void moveLeft(int speed) throws PositionException {
        super.moveLeft(speed);
    }

    @Override
    public void moveRight(int speed) throws PositionException {
        super.moveRight(speed);
    }

    @Override
    public void run() {

    }
}
