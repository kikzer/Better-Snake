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
    private static GameManager instance;

    private final Score score = new Score();

    public Timer gameTick = new Timer();
    TimerTask moveSnake = new TimerTask() {
        @Override
        public void run() {
            Snake.getInstance().move();
            updateGameState();
            UiManager.getInstance().updateGameField();
            keyHandler(GameWindow.getInstance().getGameScene());
            Snake.getInstance().selfDestroy();
        }
    };

    private GameManager() throws IOException {
        getGameTick().schedule(getMoveSnake(), 0, 200);
    }

    public static GameManager getInstance() throws IOException {
        if(instance==null){
            instance = new GameManager();
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

    public Timer getGameTick() {
        return gameTick;
    }

    public void setGameTick(Timer gameTick) {
        this.gameTick = gameTick;
    }

    public TimerTask getMoveSnake() {
        return moveSnake;
    }

    public void keyHandler(Scene gameScene) {
        gameScene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP -> {
                    Snake.getInstance().setDirectionEnum(Directions.UP);
                }
                case DOWN -> {
                    Snake.getInstance().setDirectionEnum(Directions.DOWN);
                }
                case LEFT -> {
                    Snake.getInstance().setDirectionEnum(Directions.LEFT);
                }
                case RIGHT -> {
                    Snake.getInstance().setDirectionEnum(Directions.RIGHT);
                }
            }
        });
    }

    public void checkCollision() {
        if (FoodManager.getInstance().currentFood.getPosition().getX() <= Snake.getInstance().getPositions().get(0).getX() &&
                FoodManager.getInstance().currentFood.getPosition().getY() <= Snake.getInstance().getPositions().get(0).getY() &&
                FoodManager.getInstance().currentFood.getPosition().getX() + GameField.SIZE_BLOCK >= Snake.getInstance().getPositions().get(0).getX() + GameField.SIZE_BLOCK &&
                FoodManager.getInstance().currentFood.getPosition().getY() + GameField.SIZE_BLOCK >= Snake.getInstance().getPositions().get(0).getY() + GameField.SIZE_BLOCK) {
            FoodManager.getInstance().createFood();
            Snake.getInstance().setGrowing(true);
            score.setScore(score.getScore()+1);
            System.out.println("Counter: " + score.getScore());
        }else {
            Snake.getInstance().setGrowing(false);
        }
    }
}
