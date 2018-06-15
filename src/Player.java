
/**
 * klasa gracza, zawiera informacje o nazwie gracza i liczbie zdobytych punktów
 */
public class Player {
    private static String name;
    private int points;

    public Player() {
    }
    public Player(String name, int points){
        this.name=name;
        this.points=points;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setPoints(int points){
        this.points=points;
    }

    public static String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }
}
