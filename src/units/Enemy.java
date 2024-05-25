package units;


public class Enemy extends SpaceEntity {

    public Enemy(int x, int y, double speed) {
        super(x, y, speed, "M");
    }

    public static void main(String[] args) {
        // ITS ONLY FOR TEST

        System.out.println("TEST ENEMY");
        new Enemy(1, 2, 22);
        new Enemy(1, 2, 22);
        new Enemy(1, 2, 22);
        new Enemy(1, 2, 22);
    }
}
