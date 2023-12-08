package Management.Interface;

import javafx.stage.Stage;


public class Score {
    private static Score instance;
    private int score = 0;

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
}
