package mainpackage;

import Managment.GameManager;
import Managment.Interface.GameWindow;
import javafx.application.Application;

public class Main {
    private boolean gameRunning = true;
    private GameManager manager = new GameManager();
    public static void main(String[] args) {
        Application.launch(GameWindow.class, args);
    }

    private void runGame(){
        while(gameRunning){
            manager.updateGameTick();
        }
    }
}
