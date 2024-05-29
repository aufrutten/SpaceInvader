package units;

import board.Board;
import board.PositionException;

import java.util.ArrayList;


public class Bullet extends SpaceUnit implements Runnable {

    public final static ArrayList<Bullet> bullets = new ArrayList<>();

    public Bullet(int x, int y, Board board) throws PositionException {
        super(x, y, 1, board);
        bullets.add(this);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public void moveUp(int speed) throws PositionException {
        super.moveUp(speed);
    }

    @Override
    public void moveDown(int speed) throws PositionException {
        super.moveDown(speed);
    }

    @Override
    public void run() {

    }
}

