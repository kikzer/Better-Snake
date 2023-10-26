package Managment;

import Managment.Interface.GameWindow;
import javafx.scene.Scene;

import java.util.ArrayList;

public class UiManager {
    private GameWindow gameWindow = new GameWindow();
    private ArrayList<Scene> scenes = new ArrayList<>();
    public void changeGameview(Scene scene){
        gameWindow.changeScene(scene);
    }
}
