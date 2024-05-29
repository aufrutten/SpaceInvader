package units;

import board.Board;
import board.PositionException;

import java.util.ArrayList;


public class Obstacle extends SpaceUnit {

    public final static ArrayList<Obstacle> obstacles = new ArrayList<>();

    public Obstacle(int x, int y, int size, Board board) throws PositionException {
        super(x, y, size, board);
        obstacles.add(this);
    }

    @Override
    public String toString() {
        return "O";
    }
}
