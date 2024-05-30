package units;

import javax.swing.*;
import java.awt.*;

import static menu.ScrollingImagesPanel.PANEL_WIDTH;

public class Player {
    public static int score;
    private Image image;
    private final Image imageRight;
    private final Image imageLeft;
    private final Image imageDefault;
    private int x;
    private final int y = 650;
    private boolean movingRight = false;
    private boolean movingLeft = false;
    private long lastFiretTime = 0;

    public Player() {
        imageDefault = new ImageIcon("./Sprite/player-skins/playerScaled.png").getImage();
        imageRight = new ImageIcon("./Sprite/player-skins/playerRight.png").getImage();
        imageLeft = new ImageIcon("./Sprite/player-skins/playerLeft.png").getImage();
        image = imageDefault;
        x = (PANEL_WIDTH - image.getWidth(null)) / 2;
        score = 0;
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
        g.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        g.setColor(Color.ORANGE);
        g.drawString("Score: " + score, 5, 25);
    }

    public void startMovingRight() {
        movingRight = true;
        image = imageRight;
    }

    public void stopMovingRight() {
        movingRight = false;
        image = imageDefault;
    }

    public void startMovingLeft() {
        movingLeft = true;
        image = imageLeft;
    }

    public void stopMovingLeft() {
        movingLeft = false;
        image = imageDefault;
    }

    public void update() {
        if (movingRight && x + image.getWidth(null) < PANEL_WIDTH - 10) {
            x += 10;
        }
        if (movingLeft && x >= 0) {
            x -= 10;
        }
    }

    public void fire() {
        long actualFireTime = System.currentTimeMillis();
        if (actualFireTime - lastFiretTime > 700) {
            lastFiretTime = actualFireTime;
            Bullet.bullets.add(new Bullet());
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

    public boolean checkCollision() {
        for (Alien alien : Alien.getAliens()) {
            if (getBounds().intersects(alien.getBounds())) {
                return true;
            }
        }
        return false;
    }
}