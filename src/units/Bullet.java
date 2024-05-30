// /mnt/data/Bullet.java
package units;

import javax.swing.*;
import java.awt.*;

public class Bullet {
    private int x;
    private int y;
    private final int speed = 5;
    private final Image image;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
        image = new ImageIcon("./Sprite/player-skins/bullet.png").getImage();
    }

    public void move() {
        y -= speed;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

    public boolean isOffScreen() {
        return y < 0;
    }
}
