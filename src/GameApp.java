import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameApp extends Canvas {

    ArrayList<Asteroid> asteroids = new ArrayList();
    Ship ship;
    double x,y;
    Asteroid a,b;
    public GameApp(InitData init) {
        Random rand = new Random();
        for(int i=0;i<init.asteroids;i++) {
            x = rand.nextDouble()*900;
            y = rand.nextDouble()*700;
            a= new Asteroid(x,y);
            asteroids.add(a);
        }
        ship = new Ship();
        ship.setX(450);
        ship.setY(350);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        for(int i=0;i<asteroids.size();i++) {
            b=asteroids.get(i);
            if(b != null) {
                g2d.draw(b.getShape());
            }
        }
    }
}