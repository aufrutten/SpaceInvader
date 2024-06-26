package units;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static menu.ScrollingImagesPanel.PANEL_HEIGHT;
import static menu.ScrollingImagesPanel.PANEL_WIDTH;

public class Alien extends Thread {
    public static ArrayList<Alien> aliens = new ArrayList<>();
    private final Image image;
    private int y;
    private int x;
    private boolean running = true;

    public Alien() {
        Random rand = new Random();
        if(rand.nextBoolean())
            image = new ImageIcon("./Sprite/enemy-skins/enemy.png").getImage();
        else
            image = new ImageIcon("./Sprite/enemy-skins/enemy2.png").getImage();
        x = rand.nextInt(PANEL_WIDTH - (image.getWidth(null) + 50));
        y = rand.nextInt(50);
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
    }

    public void draw(Graphics g) {
       g.drawImage(image, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

    private void move() {
        if(y<PANEL_HEIGHT)
            y += 2;
        else
            running = false;
        Random random = new Random();
        if(random.nextInt(101) == 0 && x >= image.getWidth(null) ) {
            x -= random.nextInt(20) + 5;
        } else if(random.nextInt(101) == 100 && x <= PANEL_WIDTH - image.getWidth(null))
            x += random.nextInt(20) + 5;
    }

    public static synchronized boolean borderCollision() {
        if(aliens != null) {
            for(Alien alien : Alien.getAliens()) {
                if(alien.y >= PANEL_HEIGHT - alien.image.getHeight(null))
                    return true;
            }
        }
        return false;
    }

    //Static method for Enemy management
    public static synchronized ArrayList<Alien> getAliens() {
        return aliens;
    }

    public static synchronized void spawnAliens(int number) {
        for (int i = 0; i < number; i++) {
            aliens.add(new Alien());
        }
    }

    public static synchronized void removeAlien(Alien alien) {
        alien.running = false;
        aliens.remove(alien);
    }

    public static synchronized void removeAliens() {
        for(Alien alien : aliens) {
            if(alien != null)
                alien.running = false;
        }
        aliens.clear();
    }

    public static synchronized void drawAliens(Graphics g) {
        for (Alien alien : aliens) {
            if(alien != null)
                alien.draw(g);
        }
    }
}
