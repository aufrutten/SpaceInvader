package units;

import board.Board;
import board.PositionException;


public class Void extends SpaceUnit {

    public Void(int x, int y, Board board) throws PositionException {
        super(x, y, board);
    }

    @Override
    public String toString() {
        return ".";
    }
}

