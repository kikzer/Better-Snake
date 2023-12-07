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
    private GameWindow gameWindow;
    private ArrayList<Scene> scenes = new ArrayList<>();
    private Stage currentStage;
    private Snake player;

    private final FoodManager foodManager;

    private Stage stage;
    private Scene scene;
    private Parent root;

     public static UiManager getInstance(){
        if(instance == null){
            instance = new UiManager();
        }
        return instance;
     }

    public void updateGameField(){
        getGameWindow().updateBackground();
        getPlayer().draw(getGameWindow().getGraphicContext());
        FoodManager.getInstance().currentFood.show(gameWindow.getGraphicContext());
    }



    public Stage getCurrentStage() {
        return currentStage;
    }
    public void setPlayer(Snake player) {
        this.player = player;
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
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

    public Snake getPlayer() {
        return player;
    }

    public void switchScene(SnakeScene scene) throws IOException {
        switch (scene){
            case GAMEOVER -> {
            }
            case GAME -> {
            }case MENU -> {

            }
        }

    }
}
