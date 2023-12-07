package Management;

import Environment.Food.FoodFactory;
import Environment.FoodManager;
import Environment.GameField;
import Environment.IObject;
import Management.Interface.GameWindow;
import Management.Interface.Score;
import Management.Interface.UiManager;
import Management.SnakeManagement.Directions;
import Management.SnakeManagement.Snake;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class GameManager {
    private Stage currentStage;
    private final Snake player;

    private final Score score = new Score();




    public Timer gameTick = new Timer();
    TimerTask moveSnake = new TimerTask() {
        @Override
        public void run() {
            getPlayer().move();
            updatePlayerState();
            updateGameState();
            getUiManager().updateGameField();
            keyHandler(getUiManager().getGameWindow().getGameScene());
            getPlayer().selfDestroy();
        }
    };

    private GameManager(GameWindow gameWindow) throws IOException {
        currentStage = UiManager.getInstance().getCurrentStage();
        player = new Snake(10 * GameField.SIZE_BLOCK, 5 * GameField.SIZE_BLOCK);
        getGameTick().schedule(getMoveSnake(), 0, 200);
    }

    public static GameManager getInstance(final GameWindow gameWindow) throws IOException {
        if(instance==null){
            instance = new GameManager(gameWindow);
        }
        return instance;
    }

    private void updateGameState() {
        if (!FoodManager.getInstance().getFoodExisting()) {
            FoodManager.getInstance().setFoodExisting(true);
            FoodManager.getInstance().createFood();
        }
        checkCollision();
    }

    private void updatePlayerState() {
        UiManager.getInstance().setPlayer(getPlayer());
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

    public int headPositionX;
    public int headPositionY;

    public void keyHandler(Scene gameScene) {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
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

    public void checkCollision() {
        if (FoodManager.getInstance().currentFood.getX() <= player.getPositions().get(0).getX() &&
                FoodManager.getInstance().currentFood.getY() <= player.getPositions().get(0).getY() &&
                FoodManager.getInstance().currentFood.getX() + GameField.SIZE_BLOCK >= player.getPositions().get(0).getX() + GameField.SIZE_BLOCK &&
                FoodManager.getInstance().currentFood.getY() + GameField.SIZE_BLOCK >= player.getPositions().get(0).getY() + GameField.SIZE_BLOCK) {
            FoodManager.getInstance().createFood();
            player.setGrowing(true);
            score.setScore(score.getScore()+1);
            System.out.println("Counter: " + score.getScore());
        }else {
            player.setGrowing(false);
        }
    }


}
