package Managment;

import Managment.Interface.GameWindow;
import Managment.SnakeManagement.Snake;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class UiManager {
    private GameWindow gameWindow = new GameWindow();
    private ArrayList<Scene> scenes = new ArrayList<>();
    private Stage currentStage;
    private Snake player;
    public void changeGameview(Scene scene){
        gameWindow.changeScene(scene);
    }
    public void updateGameField(){

    }

    public Stage getCurrentStage() {
        return currentStage;
    }
}
