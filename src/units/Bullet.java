package units;

import menu.gamepanels.PlayingPanel;
import java.awt.*;
import java.util.ArrayList;

public class Bullet extends Thread {
    private static ArrayList<Bullet> bullets;
    private int y;
    private final int x;
    private Rectangle rect;
    private boolean running = true;


    public Bullet() {
        x = PlayingPanel.player.getX() + 35;
        y = 600;
        rect = new Rectangle(x, y, 4, 8);
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while(running) {
            move();
            try {
                sleep(20);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, 4, 8);
    }

    public void move() {
        if(y >= 0) {
            y -= 4;
            rect.setLocation(x, y);
            if(checkCollision() || y < 0) {
                running = false;
                bullets.remove(this);
            }
        }
    }

    public boolean checkCollision() {
        if(Alien.getAliens() != null) {
            for (Alien alien: Alien.getAliens()) {
                if (rect.intersects(alien.getBounds())) {
                    Alien.removeAlien(alien);
                    return true;
                }
            }
        }
        return false;
    }

    public static void spawnBullets(int number) {
        bullets = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            bullets.add(new Bullet());
        }
    }

    public static void removeAllBullets() {
        for (Bullet bullet: bullets) {
            if (bullet != null)
                bullet.running = false;
        }
    }

    public static void drawBullets(Graphics g) {
        for (Bullet bullet : bullets) {
            if(bullet != null)
                bullet.draw(g);
        }
    }

    /*public final static ArrayList<Bullet> bullets = new ArrayList<>();

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

    } */
}

