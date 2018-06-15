/**
 * struktura do przechowywania danych inicjalizacji gry
 * @see GameApp
 */
public class InitData {
    int asteroids;
    int asteroidsSpeed;
    int bullets;
    String name;
    int score;

    public InitData(int asteroids, int asteroidsSpeed, int bullets, String name, int score) {
        this.asteroids = asteroids;
        this.asteroidsSpeed = asteroidsSpeed;
        this.bullets = bullets;
        this.name = name;
        this.score = score;
    }

    public InitData(int asteroids, int asteroidsSpeed, int bullets) {
        this.asteroids = asteroids;
        this.asteroidsSpeed = asteroidsSpeed;
        this.bullets = bullets;
    }

    public InitData() {

    }

    public InitData(String name, int score){
        this.name=name;
        this.score=score;
    }
}
