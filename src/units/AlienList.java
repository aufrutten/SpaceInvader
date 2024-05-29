package units;

import java.awt.*;
import java.util.ArrayList;

public class AlienList {
    private static ArrayList<Alien> aliens = new ArrayList<>();

    public static void spawnAliens(int number) {
        for (int i = 0; i < number; i++) {
            aliens.add(new Alien());
        }
    }

    public static ArrayList<Alien> getAliens() {
        return aliens;
    }

    public static void removeAlien(Alien alien) {
        aliens.remove(alien);
    }

    public static void move() {
        for (Alien alien : aliens) {
            alien.move();
        }
    }

    public static void draw(Graphics g) {
        for (Alien alien : aliens) {
            alien.draw(g);
        }
    }

    //TO-DO
    public static void checkCollision() {

    }
}
