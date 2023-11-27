package Management.Interface;

import GameField.FoodManager;
import Management.Interface.GameWindow;
import Management.SnakeManagement.Snake;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class UiManager {
    private GameWindow gameWindow = new GameWindow();
    private final FoodManager FOODMANAGER;
    private ArrayList<Scene> scenes = new ArrayList<>();
    private Stage currentStage;
    private Snake player;
    public void changeGameview(Scene scene){
        gameWindow.changeScene(scene);
    }

    public UiManager(GameWindow gameWindow, FoodManager FOODMANAGER){
        this.gameWindow = gameWindow;
        this.FOODMANAGER = FOODMANAGER;
    }
    public void updateGameField(){
        gameWindow.updateBackground();
        player.draw(gameWindow.getGraphicContext());
        FOODMANAGER.currentFood.show(gameWindow.getGraphicContext());
    }

    public Stage getCurrentStage() {
        return currentStage;
    }
    public void setPlayer(Snake player) {
        this.player = player;
    }
}
