package units;


import java.awt.*;

public class Enemy {

    private int x, y;
    private final int WIDTH = 30;
    private final int HEIGHT = 20;
    private boolean visible;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.visible = true;
    }

    public void draw(Graphics g) {
        if (visible) {
            g.setColor(Color.RED);
            g.fillRect(x, y, WIDTH, HEIGHT);
        }
    }

    public void move() {
        y += 1; // Aliens move downwards
        if (y > 600) {
            visible = false;
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
