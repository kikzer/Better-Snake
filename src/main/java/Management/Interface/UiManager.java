package Management.Interface;

import Management.Interface.GameWindow;
import Management.SnakeManagement.Snake;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class UiManager {
    private GameWindow gameWindow;
    private ArrayList<Scene> scenes = new ArrayList<>();
    private Stage currentStage;
    private Snake player;
    public void changeGameView(Scene scene){
        getGameWindow().changeScene(scene);
    }

    public UiManager(GameWindow gameWindow){
        this.gameWindow = gameWindow;
    }
    public void updateGameField(){
        getGameWindow().updateBackground();
        getPlayer().draw(getGameWindow().getGraphicContext());
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
}
