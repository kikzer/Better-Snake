package Management;

import GameField.GameField;
import GameField.FoodManager;
import Management.Interface.GameWindow;
import Management.Interface.UiManager;
import Management.SnakeManagement.Snake;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class GameManager {
    private final UiManager UIMANAGER;
    private final FoodManager FOODMANAGER;
    private Stage currentStage;
    private final Snake PLAYER;
    public Timer gameTick = new Timer();
    TimerTask moveSnake = new TimerTask() {
        @Override
        public void run() {
            PLAYER.move();
            updateGameState();
            UIMANAGER.updateGameField();
        }
    };
    public GameManager(GameWindow gameWindow){
        FOODMANAGER = new FoodManager();
        this.UIMANAGER = new UiManager(gameWindow, FOODMANAGER);
        currentStage = UIMANAGER.getCurrentStage();
        PLAYER = new Snake(10*GameField.SIZE_BLOCK,10*GameField.SIZE_BLOCK);
        gameTick.schedule(moveSnake,0,1000);
    }

    private void updateGameState(){
        UIMANAGER.setPlayer(PLAYER);
        if(!FOODMANAGER.getFoodExisting()){
            FOODMANAGER.setFoodExisting(true);
            FOODMANAGER.createFood();
        }
    }

    private boolean checkCollision(){
        return false;
    }
}
