

public class InitData {
    int lives;
    int asteroids;
    int hitScore;
    int asteroidsSpeed;

    public InitData(int lives, int asteroids, int hitScore, int asteroidsSpeed) {
        this.lives = lives;
        this.asteroids = asteroids;
        this.hitScore = hitScore;
        this.asteroidsSpeed = asteroidsSpeed;
    }

    public InitData() {

    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getAsteroids() {
        return asteroids;
    }

    public void setAsteroids(int asteroids) {
        this.asteroids = asteroids;
    }

    public int getHitScore() {
        return hitScore;
    }

    public void setHitScore(int hitScore) {
        this.hitScore = hitScore;
    }

    public int getAsteroidsSpeed() {
        return asteroidsSpeed;
    }

    public void setAsteroidsSpeed(int asteroidsSpeed) {
        this.asteroidsSpeed = asteroidsSpeed;
    }
}
