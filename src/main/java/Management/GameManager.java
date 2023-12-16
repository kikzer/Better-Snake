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
import java.util.Scanner;
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

    private GameManager() {
        getGameTick().schedule(getMoveSnake(), 0, 200);
    }

    public static GameManager getInstance() throws IOException {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    private void spawnTreasure(){
        int storage = 0;
        if(ObjectManager.getInstance().getObstacleExisting()){
            storage = Score.getInstance().getScore();
            
        }

    }

    private void updateGameState() {
        if (!ObjectManager.getInstance().getFoodExisting()) {
            ObjectManager.getInstance().setFoodExisting(true);
            ObjectManager.getInstance().createFood();
        }

        if (!ObjectManager.getInstance().getObstacleExisting()) {
            ObjectManager.getInstance().setObstacleExisting(true);
            ObjectManager.getInstance().createObstacle();
        }
        for (int i = 1; i < Snake.getInstance().getPositions().size() - 1; i++) {
            if (Snake.getInstance().getPositions().get(i).getX() == ObjectManager.getInstance().getCurrentFood().getPosition().getX() &&
                    Snake.getInstance().getPositions().get(i).getY() == ObjectManager.getInstance().getCurrentFood().getPosition().getY()) {
                ObjectManager.getInstance().createFood();
            }
        }
        if (ObjectManager.getInstance().getCurrenObject().getPosition().getX() == ObjectManager.getInstance().getCurrentFood().getPosition().getX() &&
                ObjectManager.getInstance().getCurrenObject().getPosition().getY() == ObjectManager.getInstance().getCurrentFood().getPosition().getY()) {

            ObjectManager.getInstance().createFood();
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
                case R -> {
                    if (Snake.getInstance().isGameOver()) {
                        gameReset();
                    }

                }
            }
        });
    }


    public void checkCollision() {
        if (ObjectManager.getInstance().getCurrentFood().getPosition().getX() <= Snake.getInstance().getPositions().get(0).getX() &&
                ObjectManager.getInstance().getCurrentFood().getPosition().getY() <= Snake.getInstance().getPositions().get(0).getY() &&
                ObjectManager.getInstance().getCurrentFood().getPosition().getX() + GameField.SIZEBLOCK >= Snake.getInstance().getPositions().get(0).getX() + GameField.SIZEBLOCK &&
                ObjectManager.getInstance().getCurrentFood().getPosition().getY() + GameField.SIZEBLOCK >= Snake.getInstance().getPositions().get(0).getY() + GameField.SIZEBLOCK) {
            ObjectManager.getInstance().createFood();
            Snake.getInstance().setGrowing(true);
            Score.getInstance().setScore(Score.getInstance().getScore() + Score.getInstance().getFoodPoint());

        } else if (ObjectManager.getInstance().getCurrenObject().getPosition().getX() <= Snake.getInstance().getPositions().get(0).getX() &&
                ObjectManager.getInstance().getCurrenObject().getPosition().getY() <= Snake.getInstance().getPositions().get(0).getY() &&
                ObjectManager.getInstance().getCurrenObject().getPosition().getX() + GameField.SIZEBLOCK >= Snake.getInstance().getPositions().get(0).getX() + GameField.SIZEBLOCK &&
                ObjectManager.getInstance().getCurrenObject().getPosition().getY() + GameField.SIZEBLOCK >= Snake.getInstance().getPositions().get(0).getY() + GameField.SIZEBLOCK) {
            if (ObjectManager.getInstance().getCurrenObject().isBlocked()) {
                Snake.getInstance().setGameOver(true);
            } else {
                Score.getInstance().setScore(Score.getInstance().getScore() + Score.getInstance().getTreasurePoint());
                ObjectManager.getInstance().createObstacle();
            }

        } else {
            Snake.getInstance().setGrowing(false);
        }
        Platform.runLater(() -> {
            UiManager.getInstance().getScoreField().setText(String.valueOf(Score.getInstance().getScore()));
        });
    }

    public void gameReset() {
        Snake.getInstance().reset();
        Score.getInstance().reset();
    }
}
