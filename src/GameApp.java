import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;
/**
 * klasa główna gry, zawiera pętlę gry, kontroluje odświeżanie i inicjalizację aktorów
 */

public class GameApp extends JPanel implements Runnable, KeyListener, ComponentListener{

    private static Thread gameLoop;
    private Graphics2D g2d;
    private InitData initData;
    private int asteroids;
    Asteroid[] a;
    private int bullets;
    private Bullet[] b;
    private int currentBullet = 0;
    private Ship s = new Ship();
    private Player p = new Player();
    private AffineTransform identity = new AffineTransform();
    private int height=800,width=1024,rrheight,rrwidth,rh,rw,rrheight1,rrwidth1,rh1,rw1;
    private double rheight=1,rwidth=1,asteroidsspeed;
    private static int level=0, lives=3, points=0;
    /**
     * inicjalizacja danych gry na podstawie struktury ini
     * @param init dane wczytane z pliku init.txt
     * @see InitData
     * @see FileParser
     */
    InitData init;
    public void startGame(){
        init = FileParser.parseInit(level);
        initData = init;
        asteroids = initData.asteroids;
        bullets = initData.bullets;
        asteroidsspeed = initData.asteroidsSpeed;
        s = new Ship();
        s.setX(width/2);
        s.setY(height/2);
        b = new Bullet[bullets];
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
            a[j].incVx(calcAngleMoveX(a[j].getMoveAngle()) * asteroidsspeed);
            a[j].incVy(calcAngleMoveY(a[j].getMoveAngle()) * asteroidsspeed);
        }

    }
    public GameApp(){
        BufferedImage backImage = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        g2d = backImage.createGraphics();
        startGame();
        addKeyListener(this);
        addComponentListener(this);
        start();
    }

    /**
     * Nadpisanie metody paintComponent, służy do przerysowywania obiektów na planszy
     */

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setTransform(identity);
        g2d.setPaint(Color.BLACK);
        g2d.fillRect(0,0,width, height);
        g2d.setColor(Color.WHITE);
        g2d.drawString("POZIOM: "+ Math.round(level+1),50,50);
        g2d.drawString("PUNKTY: "+ Math.round(points),50,70);
        g2d.drawString("ŻYCIA: "+ Math.round(lives),50,90);
        g2d.translate(s.getX(),s.getY());
        g2d.scale(rwidth,rheight);
        g2d.rotate(Math.toRadians(s.getFaceAngle()));
        g2d.setColor(Color.BLUE);
        g2d.draw(s.getShape());

        for(int i=0;i<bullets;i++){

            if(b[i].isAlive()){

                g2d.setTransform(identity);
                g2d.translate(b[i].getX(),b[i].getY());
                g2d.scale(rwidth,rheight);
                g2d.setColor(Color.GREEN);
                g2d.draw(b[i].getShape());
            }
        }
        for (int j = 0; j < asteroids; j++) {

            if (a[j].isAlive()) {
                g2d.setTransform(identity);
                g2d.translate(a[j].getX(), a[j].getY());
                g2d.scale(rwidth, rheight);
                g2d.rotate(Math.toRadians(a[j].getMoveAngle()));
                g2d.setColor(Color.pink);
                g2d.fill(a[j].getShape());
            }
        }
    }

    /**
     * obliczanie kąta poruszania się, zamiana stopni na radiany
     * @see Math
     */
    private double calcAngleMoveX(double angle) {
        return (Math.cos(angle * Math.PI / 180));
    }

    /**
     * obliczanie kąta poruszania się, zamiana stopni na radiany
     * @see Math
     */
    private double calcAngleMoveY(double angle) {
        return (Math.sin(angle * Math.PI / 180));
    }

    /**
     * uruchamianie pętli głównej gry
     * @see Thread
     */
    public void start(){
        gameLoop = new Thread(this);
        gameLoop.start();
        run();
    }

    public static void interrupt(){
        gameLoop.interrupt();
    }
    /**
     * pętla główna gry, implementacja Runnable
     * @see Runnable
     */
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

    /**
     * przerywanie pętli głównej gry
     */
    public static void stop(){
        gameLoop = null;
    }

    /**
     * aktualizacja danych gry
     */
    private void gameUpdate(){
        updateShip();
        updateBullets();
        updateAsteroids();
        checkCollisions();
    }

    /**
     * aktualizacja danych statku
     */
    private void updateShip(){
        s.incX(s.getVx());
        if(s.getX()<-10){
            s.setX(width +10);
        }else if(s.getX()>width+10){
            s.setX(-10);
        }
        s.incY(s.getVy());
        if(s.getY()<-10){
            s.setY(height +10);
        }else if(s.getY()>height+10){
            s.setY(-10);
        }
    }

    /**
     * aktualizacja danych pocisków
     */
    private void updateBullets() {
        for (int i = 0; i < bullets; i++) {
            if (b[i].isAlive()) {
                b[i].incX(b[i].getVx());
                if (b[i].getX() < 0 ||
                        b[i].getX() > width)
                {
                    b[i].setAlive(false);
                }
                b[i].incY(b[i].getVy());
                if (b[i].getY() < 0 ||
                        b[i].getY() > height)
                {
                    b[i].setAlive(false);
                }
            }
        }
    }

    /**
     * aktualizacja danych asteroid
     */
    private void updateAsteroids() {
        for (int i = 0; i < asteroids; i++) {
            if (a[i].isAlive()) {
                a[i].incX(a[i].getVx());
                if (a[i].getX() < -20)
                    a[i].setX(width + 20);
                else if (a[i].getX() > width + 20)
                    a[i].setX(-20);
                a[i].incY(a[i].getVy());

                if (a[i].getY() < -20)
                    a[i].setY(height + 20);
                else if (a[i].getY() > height + 20)
                    a[i].setY(-20);
                a[i].incMoveAngle(a[i].getRotVel());

                if (a[i].getMoveAngle() < 0)
                    a[i].setMoveAngle(360 - a[i].getRotVel());
                else if (a[i].getMoveAngle() > 359)
                    a[i].setMoveAngle(a[i].getRotVel());
            }
        }
    }

    /**
     * sprawdzanie czy nie wystąpiły kolizje między asteroidami a pociskami lub asteroidami a statkiem
     */
    private void checkCollisions() {
        for (int i = 0; i<asteroids; i++) {
            if (a[i].isAlive()) {
                for (int j = 0; j < bullets; j++) {
                    if (b[j].isAlive()) {
                        if (a[i].getBounds(rrheight,rrwidth,rh,rw).contains(
                                b[j].getX(), b[j].getY()))
                        {
                            b[j].setAlive(false);
                            a[i].setAlive(false);
                            points=points+10;
                            if(points==50){
                                level = 1;
                                inform();
                                startGame();
                            }else if(points==100){
                                level=2;
                                inform();
                                startGame();
                            }else if(points==150){
                                level=3;
                                inform();
                                startGame();
                            }
                            continue;
                        }
                    }
                }

                if (a[i].getBounds(rrheight,rrwidth,rh,rw).intersects(s.getBounds(rrheight1,rrwidth1,rh1,rw1))) {
                    a[i].setAlive(false);
                    s.setX(width/2);
                    s.setY(height/2);
                    s.setFaceAngle(0);
                    s.setVx(0);
                    s.setVy(0);
                    lives--;
                    if(lives==0){
                        stop();
                        p.setName(Window.getNameOfPlayer());
                        p.setPoints(points);
                        StopWindow stopWindow = new StopWindow();
                        stopWindow.setLocationRelativeTo(null);
                        stopWindow.setUndecorated (true);
                        stopWindow.setResizable(false);
                        stopWindow.setVisible(true);
                        level=0;
                        points=0;
                        lives=3;
                    }
                    continue;
                }
            }
        }
    }

    public void inform (){
        stop();
        JOptionPane.showMessageDialog(null, "Przechodzisz na kolejny poziom!");
            start();
    }
    /**
     * implementacja KeyListener
     * @see KeyListener
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * obsluga przyciskow sterujacych gra
     * @see KeyListener
     */
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
                s.incVx(calcAngleMoveX(s.getMoveAngle()) * 0.2);
                s.incVy(calcAngleMoveY(s.getMoveAngle()) * 0.2);
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

    /**
     * @see KeyListener
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
    public void componentHidden(ComponentEvent ce) {}
    public void componentShown(ComponentEvent ce) {}
    public void componentMoved(ComponentEvent ce) { }
    public void componentResized(ComponentEvent ce) {
        height = this.getHeight();
        width = this.getWidth();
        rheight=((double)height/750);
        rwidth=((double)width/974);
        rrheight = (int)(rheight*24);
        rrwidth= (int)(rwidth*24);
        rh=(int)(rheight*50);
        rw=(int)(rwidth*50);
        rrheight1 = (int)(rheight*5);
        rrwidth1= (int)(rwidth*5);
        rh1=(int)(rheight*14);
        rw1=(int)(rwidth*14);
        s.setX(width/2);
        s.setY(height/2);
    }

    public static int getLives() {
        return lives;
    }

    public static int getPoints() {
        return points;
    }

}