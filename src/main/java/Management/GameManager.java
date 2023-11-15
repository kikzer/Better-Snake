package Management;

import GameField.GameField;
import Management.Interface.GameWindow;
import Management.Interface.UiManager;
import Management.SnakeManagement.Snake;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class GameManager {
    private final UiManager uiManager;
    private Stage currentStage;
    private final Snake player;
    public Timer gameTick = new Timer();
    TimerTask moveSnake = new TimerTask() {
        @Override
        public void run() {
            getPlayer().move();
            updatePlayerState();
            getUiManager().updateGameField();
        }
    };
    public GameManager(GameWindow gameWindow){
        this.uiManager = new UiManager(gameWindow);
        currentStage = uiManager.getCurrentStage();
        player = new Snake(10*GameField.SIZE_BLOCK,5*GameField.SIZE_BLOCK);
        getGameTick().schedule(getMoveSnake(),0,1000);
    }

    private void updatePlayerState(){
        getUiManager().setPlayer(getPlayer());
    }

    public UiManager getUiManager() {
        return uiManager;
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public Snake getPlayer() {
        return player;
    }

    public Timer getGameTick() {
        return gameTick;
    }

    public void setGameTick(Timer gameTick) {
        this.gameTick = gameTick;
    }

    public TimerTask getMoveSnake() {
        return moveSnake;
    }

    public void setMoveSnake(TimerTask moveSnake) {
        this.moveSnake = moveSnake;
    }
}
