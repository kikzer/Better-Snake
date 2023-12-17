package Management.Interface;

import javafx.stage.Stage;

/**
 * creates a score for the game Better Snake with different Score points for different
 * eatable Objects
 */
public class Score {
    private static Score instance;
    private int score = 0;

    private final int foodPoint = 1, treasurePoint = 5;

    public static Score getInstance(){
        if(instance == null){
            instance = new Score();
        }
        return instance;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void reset(){
        score = 0;
    }

    public int getFoodPoint() {
        return foodPoint;
    }

    public int getTreasurePoint() {
        return treasurePoint;
    }
}
