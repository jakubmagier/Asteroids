
/**
 * klasa gracza, zawiera informacje o liczbie punktow i zyciach
 */
public class Player {
    private static int lives;
    private static int points;

    public Player() {
        lives = 4;
        points = 0;
    }

    public static int getLives() {
        return lives;
    }

    public static int getPoints() {
        return points;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
