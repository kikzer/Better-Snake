package Management;

import Environment.GameField;
import Management.Interface.GameWindow;
import Management.Interface.Score;
import Management.Interface.UiManager;
import Management.SnakeManagement.Directions;
import Management.SnakeManagement.Snake;
import javafx.application.Platform;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class GameManager {
    private static GameManager instance;
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
        if (instance == null) {
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
            Score.getInstance().setScore(Score.getInstance().getScore() + 1);

            System.out.println("Counter: " + Score.getInstance().getScore());
        } else if (ObstacleManager.getInstance().getCurrenObject().getPosition().getX() <= Snake.getInstance().getPositions().get(0).getX() &&
                ObstacleManager.getInstance().getCurrenObject().getPosition().getY() <= Snake.getInstance().getPositions().get(0).getY() &&
                ObstacleManager.getInstance().getCurrenObject().getPosition().getX() + GameField.SIZEBLOCK >= Snake.getInstance().getPositions().get(0).getX() + GameField.SIZEBLOCK &&
                ObstacleManager.getInstance().getCurrenObject().getPosition().getY() + GameField.SIZEBLOCK >= Snake.getInstance().getPositions().get(0).getY() + GameField.SIZEBLOCK) {
            if (ObstacleManager.getInstance().getCurrenObject().isBlocked()) {
                Snake.getInstance().setGameOver(true);
            } else {
                Score.getInstance().setScore(Score.getInstance().getScore() + 5);
                ObstacleManager.getInstance().createObstacle();
            }

        } else {
            Snake.getInstance().setGrowing(false);
        }
        Platform.runLater(() -> {
            UiManager.getInstance().getScoreField().setText(String.valueOf(Score.getInstance().getScore()));
        });
    }
}
