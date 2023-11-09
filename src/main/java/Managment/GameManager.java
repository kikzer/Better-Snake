package Managment;

import Managment.SnakeManagement.Snake;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class GameManager {
    private final UiManager UIMANAGER;
    private Stage currentStage;
    private final Snake PLAYER;

    public GameManager(){
        this.UIMANAGER = new UiManager();
        currentStage = UIMANAGER.getCurrentStage();
        PLAYER = new Snake(100,100);
    }
    public void updateGameTick(){
        Timer gameTick = new Timer();
        TimerTask moveSnake = new TimerTask() {
            @Override
            public void run() {
                PLAYER.move();
                UIMANAGER.updateGameField();
            }
        };
        currentStage.setOnCloseRequest(event -> {
            //System.exit(0);
            gameTick.cancel();
        });
        gameTick.schedule(moveSnake,0,1000);
    }
}
