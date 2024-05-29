package units;

import board.Board;
import board.PositionException;

import java.util.ArrayList;

public class Enemy extends SpaceUnit implements Runnable {

    public final static ArrayList<Enemy> enemies = new ArrayList<>();

    public Enemy(int x, int y, int size, Board board) throws PositionException {
        super(x, y, size, board);
        enemies.add(this);
    }

    @Override
    public String toString() {
        return "E";
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
