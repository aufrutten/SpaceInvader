package units;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static menu.ScrollingImagesPanel.PANEL_WIDTH;

public class Player {
    private Image image;
    private int x;
    private final int y = 650;

    public Player() {
        image = new ImageIcon("./Sprite/player-skins/playerScaled.png").getImage();
        x = (PANEL_WIDTH - image.getWidth(null)) / 2;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public void moveRight() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int counter = 3;
            @Override
            public void run() {
                if(x + image.getWidth(null) < PANEL_WIDTH - 10)
                    x += 2;
                counter--;
                if(counter == 0)
                    timer.cancel();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 10);
    }

    public void moveLeft() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int counter = 3;
            @Override
            public void run() {
                if(x >= 0)
                    x -= 2;
                counter--;
                if(counter == 0)
                    timer.cancel();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 10);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

    public boolean checkCollision() {
        for (Alien alien : Alien.getAliens()) {
            if(getBounds().intersects(alien.getBounds()))
                return true;
        }
        return false;
    }
}
