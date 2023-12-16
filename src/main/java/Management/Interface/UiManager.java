package Management.Interface;

import Environment.GameField;
import Management.ObjectManager;
import Management.SnakeManagement.Snake;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class UiManager {
    private final ArrayList<Scene> scenes = new ArrayList<>();
    private Stage currentStage;
    private final Label scoreField = new Label();
    private static UiManager instance;

    private UiManager() {
        scoreField.setTextFill(Color.BLACK);
        scoreField.setFont(new Font("Balloon", 24));
        scoreField.setTranslateY(GameWindow.HEIGHT);
        scoreField.setTranslateX(GameField.SIZEBLOCK * 2);
    }

    public static UiManager getInstance() {
        if (instance == null) {
            instance = new UiManager();
        }
        return instance;
    }

    public void updateGameField() {
        GameWindow.getInstance().updateBackground();
        Snake.getInstance().draw(GameWindow.getInstance().getGraphicContext());
        ObjectManager.getInstance().getCurrentFood().show(GameWindow.getInstance().getGraphicContext());
        if (ObjectManager.getInstance().getCurrenObject() != null)
            ObjectManager.getInstance().getCurrenObject().show(GameWindow.getInstance().getGraphicContext());
    }



    public Label getScoreField() {
        return scoreField;
    }

}
