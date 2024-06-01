import board.Board;
import board.PositionException;
import units.Defender;
import units.Enemy;
import units.SpaceUnit;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws PositionException {
        Board board = new Board(70, 20);

        for (SpaceUnit[] i : board) {
            System.out.println(Arrays.toString(i));
        }
        int x = 5;
        int y = 4;

        Defender defender = new Defender(x, y,2, board);
        Enemy enemy = new Enemy(10, 8, 2, board);

        System.out.println(board);
        System.out.println(board.getPosition(0, 0));
//        enemy.moveDown(2);
//
//        defender.moveLeft(4);
//        defender.moveRight(4);
//        defender.moveRight(4);
//        System.out.println(board);


        // X and Y it will be center of object

    }
}
