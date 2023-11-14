package Management.Interface;

import Management.Interface.GameWindow;
import Management.SnakeManagement.Snake;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class UiManager {
    private GameWindow gameWindow = new GameWindow();
    private ArrayList<Scene> scenes = new ArrayList<>();
    private Stage currentStage;
    private Snake player;
    public void changeGameview(Scene scene){
        gameWindow.changeScene(scene);
    }

    public UiManager(GameWindow gameWindow){
        this.gameWindow = gameWindow;
    }
    public void updateGameField(){
        gameWindow.updateBackground();
        player.draw(gameWindow.getGraphicContext());
    }

    public Stage getCurrentStage() {
        return currentStage;
    }
    public void setPlayer(Snake player) {
        this.player = player;
    }
}
