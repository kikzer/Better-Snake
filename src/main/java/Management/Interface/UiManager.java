package Management.Interface;

import Environment.FoodManager;
import Management.SnakeManagement.Snake;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

//TODO Scenes in Factories umwandeln maybe
public class UiManager {
    private ArrayList<Scene> scenes = new ArrayList<>();
    private Stage currentStage;
    private Snake player;

    private final FoodManager foodManager;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static UiManager getInstance() {
        if (instance == null) {
            instance = new UiManager();
        }
        return instance;
    }

    public void updateGameField() {
        GameWindow.getInstance().updateBackground();
        Snake.getInstance().draw(GameWindow.getInstance().getGraphicContext());
        FoodManager.getInstance().currentFood.show(GameWindow.getInstance().getGraphicContext());

    }


    public Stage getCurrentStage() {
        return currentStage;
    }
    public void setPlayer(Snake player) {
        this.player = player;
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

}
