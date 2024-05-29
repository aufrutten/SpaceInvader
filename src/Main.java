import board.Board;
import board.PositionException;
import units.Defender;
import units.Enemy;

public class Main {

    public static void main(String[] args) throws PositionException {
        Board board = new Board(70, 20);

        int x = 5;
        int y = 4;

        Defender defender = new Defender(x, y,2, board);
        Enemy enemy = new Enemy(10, 8, 2, board);

        System.out.println(board);
        defender.moveLeft(1);
        defender.moveLeft(1);
        defender.moveLeft(1);
        System.out.println(board);


        // X and Y it will be center of object

    }
}
