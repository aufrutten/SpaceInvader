package units;

import menu.gamepanels.PlayingPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Bullet extends Thread {
    public static final ArrayList<Bullet> bullets = new ArrayList<>();
    private static final Object lock = new Object();
    private int y;
    private final int x;
    private final Image image;
    private boolean running = true;

    public Bullet() {
        x = PlayingPanel.player.getX() + 35;
        y = 600;
        image = new ImageIcon("./Sprite/player-skins/bullet.png").getImage();
        synchronized (lock) {
            bullets.add(this);
        }
        Thread thread = new Thread(this);
        thread.start();
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

    @Override
    public void run() {
        while (running) {
            move();
            try {
                sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void draw(Graphics g) {
        if (running) {
            g.drawImage(image, x, y, null);
        }
    }

    public void move() {
        if (y >= 0) {
            y -= 6;
            if (checkCollision() || y < 0) {
                running = false;
                synchronized (lock) {
                    bullets.remove(this);
                }
            }
        }
    }

    public boolean checkCollision() {
        synchronized (lock) {
            if (Alien.getAliens() != null) {
                for (Alien alien : Alien.getAliens()) {
                    if (getBounds().intersects(alien.getBounds())) {
                        Alien.removeAlien(alien);
                        Player.score++;
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static void removeAllBullets() {
        synchronized (lock) {
            for (Bullet bullet : bullets) {
                if (bullet != null)
                    bullet.running = false;
            }
            bullets.clear();
        }
    }

    public static void drawBullets(Graphics g) {
        synchronized (lock) {
            for (Bullet bullet : bullets) {
                if (bullet != null)
                    bullet.draw(g);
            }
        }
    }
}
