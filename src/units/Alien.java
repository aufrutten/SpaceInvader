package units;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static menu.ScrollingImagesPanel.PANEL_HEIGHT;
import static menu.ScrollingImagesPanel.PANEL_WIDTH;

public class Alien extends Thread {
    private Image image;
    private int y;
    private int x;
    private boolean running = true;

    public Alien() {
        Random rand = new Random();
        if(rand.nextBoolean())
            image = new ImageIcon("./Sprite/enemy-skins/enemy.png").getImage();
        else
            image = new ImageIcon("./Sprite/enemy-skins/enemy2.png").getImage();
        x = rand.nextInt(PANEL_WIDTH - image.getWidth(null));
        y = rand.nextInt(100);
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

    public void move() {
        if(y<=PANEL_HEIGHT)
            y += 2;
        else
            running = false;
        Random random = new Random();
        if(random.nextInt(101) == 0 && x >= 0 ) {
            x -= random.nextInt(20) + 5;
        } else if(random.nextInt(101) == 100 && x <= PANEL_WIDTH - image.getWidth(null))
            x += random.nextInt(20) + 5;
    }

}
