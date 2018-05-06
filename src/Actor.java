import java.awt.*;

public class Actor {
    private Shape shape;
    private double x, y;
    private double vx, vy;
    private double moveAngle, faceAngle;
    private boolean alive;


    public Actor() {
        setShape(null);
        setAlive(false);
        setX(0.0);
        setY(0.0);
        setMoveAngle(0.0);
        setVx(0.0);
        setVy(0.0);
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

    public boolean isAlive(){
        return alive;
    }
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public void setAlive(boolean alive){
        this.alive = alive;
    }

    public void setMoveAngle(double moveAngle) {
        this.moveAngle = moveAngle;
    }

    public void setFaceAngle(double faceAngle) {
        this.faceAngle = faceAngle;
    }

    public void incX(double i){
        this.x += i;
    }

    public void incY(double i){
        this.y += i;
    }

    public void incVx(double i){
        this.vx += i;
    }

    public void incVy(double i){
        this.vy += i;
    }

    public void decVx(double i){
        this.vx -= i;
    }

    public void decVy(double i){
        this.vy -= i;
    }

    public void incMoveAngle(double i){
        this.moveAngle += i;
    }

    public void incFaceAngle(double i){
        this.faceAngle += i;
    }


}
