import java.awt.*;

/**
 * klasa statku, ustala jego kształt
 * @see Actor
 */
public class Ship extends Actor {
    private int[] shipx = {-7,-4,1,4,7,1};
    private int[] shipy = {7,8,8,8,7,-8};

    public Rectangle getBounds() {
        Rectangle r = new Rectangle((int)getX() - 5, (int)getY() - 5, 14, 14);
        return r;
    }
    public Rectangle getBounds(int rrheight, int rrwidth, int rh, int rw) {
        Rectangle r = new Rectangle(((int)getX() - rrwidth), (int)getY() - rrheight, rw, rh);
        return r;
    }
    public Ship() {
        setShape(new Polygon(shipx, shipy, shipx.length));
        setAlive(true);
    }
}
