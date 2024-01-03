package Management.Interface;

import Environment.GameField;
import Management.ObjectManager;
import Management.SnakeManagement.Snake;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * This class manages the ui of the program.
 * It draws everything on screen.
 */
public class UiManager {
    private ArrayList<Scene> scenes = new ArrayList<>();
    private Stage currentStage;
    private Label scoreField = new Label();
    private Label highScoreField = new Label();
    private static UiManager instance;
    private static final Logger uiManagerLogger = LogManager.getLogger(UiManager.class);

    private UiManager(){
        scoreField.setTextFill(Color.BLACK);
        scoreField.setFont(new Font("Balloon", 24));
        scoreField.setTranslateY(GameWindow.getInstance().getHeight()+5);
        scoreField.setTranslateX(GameWindow.getInstance().getSizeBlock());

        highScoreField.setTextFill(Color.BLACK);
        highScoreField.setFont(new Font("Balloon", 24));
        highScoreField.setTranslateY(GameWindow.getInstance().getHeight()+5);
        highScoreField.setTranslateX(GameWindow.getInstance().getSizeBlock()*17);
    }

    public static UiManager getInstance() {
        if (instance == null) {
            instance = new UiManager();
            uiManagerLogger.log(Level.DEBUG, "UiManager instamce created");
        }
        return instance;
    }

    /**
     * Method updates all elements on the gamefield to display the current game frame.
     */
    public void updateGameField() {
        GameWindow.getInstance().updateBackground();
        ObjectManager.getInstance().getCurrentFood().show(GameWindow.getInstance().getGraphicContext());
        if(ObjectManager.getInstance().getCurrenTreasure() != null){
            ObjectManager.getInstance().getCurrenTreasure().show(GameWindow.getInstance().getGraphicContext());
        }
        for(int i = 0; i < ObjectManager.getInstance().getWallStructures().length;i++){
            ObjectManager.getInstance().getWallStructures()[i].showStructure();
        }
        Snake.getInstance().draw(GameWindow.getInstance().getGraphicContext());

    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public ArrayList<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(ArrayList<Scene> scenes) {
        this.scenes = scenes;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public Label getScoreField() {
        return scoreField;
    }

    public void setScoreField(Label scoreField) {
        this.scoreField = scoreField;
    }

    public Label getHighScoreField() {
        return highScoreField;
    }

    public void setHighScoreField(Label highScoreField) {
        this.highScoreField = highScoreField;
    }
}
