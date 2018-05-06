import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameApp extends JPanel implements Runnable, KeyListener{

    private Thread gameLoop;
    private Graphics2D g2d;
    private InitData initData = new InitData();
    private int asteroids;
    Asteroid[] a;
    private int bullets;
    private Bullet[] b;
    private int currentBullet = 0;
    private Ship s = new Ship();
    private AffineTransform identity = new AffineTransform();

    public GameApp(InitData ini){
        BufferedImage backImage = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        g2d = backImage.createGraphics();
        s.setX(320);
        s.setY(240);
        initData = ini;
        asteroids = initData.asteroids;
        bullets = initData.bullets;
        b = b = new Bullet[bullets];
        a = new Asteroid[asteroids];
        for(int i=0; i<bullets;i++){
            b[i] = new Bullet();
        }
        for(int j=0; j<asteroids;j++){
            a[j] = new Asteroid();
            Random rand = new Random();
            a[j].setRotVel((double) rand.nextInt(2)+1);
            a[j].setX((double) rand.nextInt(500)+40);
            a[j].setY((double) rand.nextInt(3800)+40);
            a[j].setMoveAngle(rand.nextInt(330));
            double ang = a[j].getMoveAngle()-90;
            a[j].setVx(calcAngleMoveX(ang));
            a[j].setVy(calcAngleMoveY(ang));
        }
        addKeyListener(this);
        start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setTransform(identity);
        g2d.setPaint(Color.BLACK);
        g2d.fillRect(0,0,getSize().width,getSize().height);
        g2d.setColor(Color.WHITE);

        g2d.setTransform(identity);
        g2d.translate(s.getX(),s.getY());
        g2d.rotate(Math.toRadians(s.getFaceAngle()));
        g2d.setColor(Color.BLUE);
        g2d.draw(s.getShape());

        for(int i=0;i<bullets;i++){

            if(b[i].isAlive()){

                g2d.setTransform(identity);
                g2d.translate(b[i].getX(),b[i].getY());
                g2d.setColor(Color.GREEN);
                g2d.draw(b[i].getShape());
            }
        }
        for (int j = 0; j < asteroids; j++) {

            if (a[j].isAlive()) {
                g2d.setTransform(identity);
                g2d.translate(a[j].getX(), a[j].getY());
                g2d.rotate(Math.toRadians(a[j].getMoveAngle()));
                g2d.setColor(Color.pink);
                g2d.fill(a[j].getShape());
            }
        }
    }

    private double calcAngleMoveX(double angle) {
        return (Math.cos(angle * Math.PI / 180));
    }

    private double calcAngleMoveY(double angle) {
        return (Math.sin(angle * Math.PI / 180));
    }

    public void start(){
        gameLoop = new Thread(this);
        gameLoop.start();
        run();
    }

    public void run(){
        Thread thread = Thread.currentThread();
        while (thread == gameLoop){
            try{
                gameUpdate();
                thread.sleep(20);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            repaint();
        }
    }

    public void stop(){
        gameLoop = null;
    }

    private void gameUpdate(){
        updateShip();
        updateBullets();
        updateAsteroids();
        checkCollisions();
    }

    private void updateShip(){
        s.incX(s.getVx());
        if(s.getX()<-10){
            s.setX(getSize().width +10);
        }else if(s.getX()>getSize().width+10){
            s.setX(-10);
        }
        s.incY(s.getVy());
        if(s.getY()<-10){
            s.setY(getSize().height +10);
        }else if(s.getY()>getSize().height+10){
            s.setY(-10);
        }
    }

    private void updateBullets() {
        for (int i = 0; i < bullets; i++) {
            if (b[i].isAlive()) {
                b[i].incX(b[i].getVx());
                if (b[i].getX() < 0 ||
                        b[i].getX() > getSize().width)
                {
                    b[i].setAlive(false);
                }
                b[i].incY(b[i].getVy());
                if (b[i].getY() < 0 ||
                        b[i].getY() > getSize().height)
                {
                    b[i].setAlive(false);
                }
            }
        }
    }

    private void updateAsteroids() {
        for (int i = 0; i < asteroids; i++) {
            if (a[i].isAlive()) {
                a[i].incX(a[i].getVx());
                if (a[i].getX() < -20)
                    a[i].setX(getSize().width + 20);
                else if (a[i].getX() > getSize().width + 20)
                    a[i].setX(-20);
                a[i].incY(a[i].getVy());

                if (a[i].getY() < -20)
                    a[i].setY(getSize().height + 20);
                else if (a[i].getY() > getSize().height + 20)
                    a[i].setY(-20);
                a[i].incMoveAngle(a[i].getRotVel());

                if (a[i].getMoveAngle() < 0)
                    a[i].setMoveAngle(360 - a[i].getRotVel());
                else if (a[i].getMoveAngle() > 359)
                    a[i].setMoveAngle(a[i].getRotVel());
            }
        }
    }

    private void checkCollisions() {
        for (int i = 0; i<asteroids; i++) {
            if (a[i].isAlive()) {
                for (int j = 0; j < bullets; j++) {
                    if (b[j].isAlive()) {
                        if (a[i].getBounds().contains(
                                b[j].getX(), b[j].getY()))
                        {
                            b[j].setAlive(false);
                            a[i].setAlive(false);
                            continue;
                        }
                    }
                }

                if (a[i].getBounds().intersects(s.getBounds())) {
                    a[i].setAlive(false);
                    s.setX(320);
                    s.setY(240);
                    s.setFaceAngle(0);
                    s.setVx(0);
                    s.setVy(0);
                    continue;
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                s.incFaceAngle(-5);
                if (s.getFaceAngle() < 0) s.setFaceAngle(360-5);
                break;
            case KeyEvent.VK_RIGHT:
                s.incFaceAngle(5);
                if (s.getFaceAngle() > 360) s.setFaceAngle(5);
                break;
            case KeyEvent.VK_UP:
                s.setMoveAngle(s.getFaceAngle() - 90);
                s.incVx(calcAngleMoveX(s.getMoveAngle()) * 0.1);
                s.incVy(calcAngleMoveY(s.getMoveAngle()) * 0.1);
                break;
            case KeyEvent.VK_DOWN:
                s.setMoveAngle(s.getFaceAngle() - 90);
                s.decVx(calcAngleMoveX(s.getMoveAngle()) * 0.1);
                s.decVy(calcAngleMoveY(s.getMoveAngle()) * 0.1);
                break;
            case KeyEvent.VK_SPACE:
                currentBullet++;
                if (currentBullet > bullets - 1) currentBullet = 0;
                b[currentBullet].setAlive(true);
                b[currentBullet].setX(s.getX());
                b[currentBullet].setY(s.getY());
                b[currentBullet].setMoveAngle(s.getFaceAngle() - 90);

                double angle = b[currentBullet].getMoveAngle();
                double svx = s.getVx();
                double svy = s.getVy();
                b[currentBullet].setVx(svx + calcAngleMoveX(angle) * 2);
                b[currentBullet].setVy(svy + calcAngleMoveY(angle) * 2);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }

}