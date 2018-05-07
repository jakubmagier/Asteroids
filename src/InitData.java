/**
 * struktura do przechowywania danych inicjalizacji gry
 * @see GameApp
 */
public class InitData {
    int lives;
    int asteroids;
    int hitScore;
    int asteroidsSpeed;
    int bullets;

    public InitData(int lives, int asteroids, int hitScore, int asteroidsSpeed, int bullets) {
        this.lives = lives;
        this.asteroids = asteroids;
        this.hitScore = hitScore;
        this.asteroidsSpeed = asteroidsSpeed;
        this.bullets = bullets;
    }

    public InitData() {

    }
}
