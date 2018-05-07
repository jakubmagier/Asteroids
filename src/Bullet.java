import java.awt.*;

/**
 * klasa kuli, ustala jej kształt
 * @see Actor
 */

public class Bullet extends Actor {

    public Bullet(){
        setShape(new Rectangle(0,0,1,1));
        setAlive(false);
    }
}
