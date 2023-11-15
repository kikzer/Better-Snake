package Management;

import GameField.GameField;
import Management.Interface.GameWindow;
import Management.Interface.UiManager;
import Management.SnakeManagement.Directions;
import Management.SnakeManagement.Snake;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
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
            keyHandler(getUiManager().getGameWindow().getGameScene());
            if (checkCollision(getUiManager().getGameWindow().getGameField().getFood().getX(),getUiManager().getGameWindow().getGameField().getFood().getY())){
                getPlayer().setEaten(true);
                getUiManager().getGameWindow().getGameField().createdRandomFood();
            }
        }
    };
    public GameManager(GameWindow gameWindow){
        this.uiManager = new UiManager(gameWindow);
        currentStage = uiManager.getCurrentStage();
        player = new Snake(10*GameField.SIZE_BLOCK,5*GameField.SIZE_BLOCK);
        getGameTick().schedule(getMoveSnake(),0,500);
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

    public void keyHandler(Scene gameScene){
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case UP -> {
                        getPlayer().setDirectionEnum(Directions.UP);
                    }
                    case DOWN -> {
                        getPlayer().setDirectionEnum(Directions.DOWN);
                    }
                    case LEFT -> {
                        getPlayer().setDirectionEnum(Directions.LEFT);
                    }
                    case RIGHT -> {
                        getPlayer().setDirectionEnum(Directions.RIGHT);
                    }
                }
            }
        });
    }
    public boolean checkCollision(final int obstaclePositionX, final int obstaclePositionY){
        return getPlayer().getYPositions().get(0) >= obstaclePositionY && getPlayer().getXPositions().get(0) >= obstaclePositionX &&
                getPlayer().getYPositions().get(0) + GameField.SIZE_BLOCK <= obstaclePositionY + GameField.SIZE_BLOCK;

    }

}
