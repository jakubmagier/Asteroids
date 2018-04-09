import java.awt.*;

public class Asteroid extends Actor {
    int astx[] = {6, 7, 7, 7, 6, -7};
    int asty[] = {-6, -3, 0, 3, 6, 0};

    protected double rotVel;

    public double getRotVel() {
        return rotVel;
    }

    public void setRotVel(double rotVel) {
        this.rotVel = rotVel;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)getX() - 6, (int)getY() - 6, 12, 12);
    }

    public Asteroid(double x, double y) {
        super(x, y);
        this.setShape(new Rectangle((int)getX() - 6, (int)getY() - 6, 12, 12));
    }

    public Asteroid() {

    }
}
