import java.awt.*;

public class Ship extends Actor {

    private int[] shipx = {-6, -3, 0, 3, 6, 0};
    private int[] shipy = {6, 7, 7, 7, 6, -7};

    public Rectangle getBounds() {
        return new Rectangle((int)getX() - 6, (int)getY() - 6, 12, 12);
    }

    public Ship() {
        setShape(new Polygon(shipx, shipy, shipx.length));

    }

}
