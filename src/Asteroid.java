import java.awt.*;

/**
 * klasa asteroidy, ustala jej kształt
 * @see Actor
 */

public class Asteroid extends Actor {
    private int astx[] = {-18, -11, -2, 18, 20, 18, 10, 0, -8};
    private int asty[] = {18, 21, 15, 18, 14, -18, -20, -12, -15};

    protected double rotVel;

    public double getRotVel() {
        return rotVel;
    }

    public void setRotVel(double rotVel) {
        this.rotVel = rotVel;
    }

    public Rectangle getBounds() {
        Rectangle r = new Rectangle((int)getX() - 24, (int)getY() - 24, 50, 50);
        return r;
    }
    public Rectangle getBounds(int rrheight, int rrwidth, int rh, int rw) {
        Rectangle r = new Rectangle(((int)getX() - rrwidth), (int)getY() - rrheight, rw, rh);
        return r;
    }

    public Asteroid() {
        setShape(new Polygon(astx, asty, astx.length));
        setRotVel(0.0);
        setAlive(true);
    }
}
