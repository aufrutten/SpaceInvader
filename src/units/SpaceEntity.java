package units;


import java.awt.event.KeyEvent;

public abstract class SpaceEntity {
    protected int x;
    protected int y;
    private int dx;
    protected double speed;
    protected final String image;

    protected SpaceEntity(int x, int y, double speed, String image) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.image = image;
    }

    public String getImage() {
        return this.image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }



}
