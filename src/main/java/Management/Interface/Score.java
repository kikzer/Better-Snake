package Management.Interface;

import Management.GameManager;
import javafx.stage.Stage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * creates a score for the game Better Snake with different Score points for different
 * eatable Objects
 */
public class Score {

    private static final Logger scoreLogger = LogManager.getLogger(Score.class);

    private static Score instance;
    private int score = 0;

    private int highScore = score;

    private final int foodPoint = 1, treasurePoint = 5;

    public static Score getInstance(){
        if(instance == null){
            instance = new Score();
        }
        return instance;
    }

    private Score(){
        readHighScoreFile();
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highscore) {
        this.highScore = highscore;
    }

    private void readHighScoreFile(){
        String path = "src/main/resources/HighScore.txt";

        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            if (!lines.isEmpty()) {
                highScore = Integer.parseInt(lines.get(0));
            }
        } catch (IOException e) {
            scoreLogger.log(Level.DEBUG, "HighScore.txt couldn't be read");
        }
    }

    public void updateHighScoreFile(){
        String pfad = "src/main/resources/HighScore.txt"; // Ersetzen Sie dies durch den Pfad zu Ihrer Datei
        String content = "" + highScore;

        try {
            Files.write(Paths.get(pfad), content.getBytes());
        } catch (IOException e) {
            scoreLogger.log(Level.DEBUG, "HighScore.txt couldn't be updated");
        }
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
