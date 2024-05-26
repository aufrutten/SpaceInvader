package units;

import java.awt.*;


public class Bullet {

    private int x, y;
    private final int WIDTH = 5;
    private final int HEIGHT = 10;
    private boolean visible;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
        this.visible = true;
    }

    public void draw(Graphics g) {
        if (visible) {
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, WIDTH, HEIGHT);
        }
    }

    public void move() {
        y -= 2;
        if (y < 0) {
            visible = false;
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}


