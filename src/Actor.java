import java.awt.*;


public class Actor {
    private Shape shape;
    private double x, y;
    private double vx, vy;
    private double moveAngle, faceAngle;


    public Actor() {

    }
    public Actor(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Shape getShape() {
        return shape;
    }

    public double getX() {

        return x;
    }

    public double getY() {

        return y;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public double getMoveAngle() {
        return moveAngle;
    }

    public double getFaceAngle() {
        return faceAngle;
    }


    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setX(double x) {
        this.x = x;
        this.setShape(new Rectangle((int)getX() - 6, (int)getY() - 6, 12, 12));
    }

    public void setY(double y) {
        this.y = y;
        this.setShape(new Rectangle((int)getX() - 6, (int)getY() - 6, 12, 12));
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public void setMoveAngle(double moveAngle) {
        this.moveAngle = moveAngle;
    }

    public void setFaceAngle(double faceAngle) {
        this.faceAngle = faceAngle;
    }
}
