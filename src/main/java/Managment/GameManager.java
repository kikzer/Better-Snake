package Managment;

import Managment.Interface.GameWindow;
import Managment.SnakeManagement.Snake;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class GameManager {
    private final UiManager UIMANAGER;
    private Stage currentStage;
    private final Snake PLAYER;
    public Timer gameTick = new Timer();
    TimerTask moveSnake = new TimerTask() {
        @Override
        public void run() {
            PLAYER.move();
            updatePlayerState();
            UIMANAGER.updateGameField();
        }
    };
    public GameManager(GameWindow gameWindow){
        this.UIMANAGER = new UiManager(gameWindow);
        currentStage = UIMANAGER.getCurrentStage();
        PLAYER = new Snake(290,390);
        gameTick.schedule(moveSnake,0,1000);
    }

    private void updatePlayerState(){
        UIMANAGER.setPlayer(PLAYER);
    }
}
