package Management;

import Environment.GameField;
import Environment.IWallStructure;
import Environment.Obstacle.Wall;
import Environment.Obstacle.WallStructure;
import Management.Interface.GameWindow;
import Management.Interface.Score;
import Management.Interface.UiManager;
import Management.SnakeManagement.Directions;
import Management.SnakeManagement.Snake;
import javafx.application.Platform;
import javafx.scene.Scene;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class GameManager {
    private static GameManager instance;

    private static final Logger keyInputLogger = LogManager.getLogger(GameManager.class);

    private boolean gameWon = false;
    public Timer gameTick = new Timer();

    /**
     * Tick system which makes it possible to have a working game flow using Runnable
     */
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

    /**
     * spawns a Treasure-box every 15 points on a random tile of the game field
     */
    private void spawnTreasure() {
        if (ObjectManager.getInstance().getCurrenObject() == null && !ObjectManager.getInstance().getObstacleExisting()) {
            ObjectManager.getInstance().setObstacleExisting(true);
            ObjectManager.getInstance().createObstacle();
            System.out.println("new treasure");
        } else {
            if (Score.getInstance().getScore() % 15 == 0 && ObjectManager.getInstance().getCurrenObject() == null) {
                ObjectManager.getInstance().setObstacleExisting(false);
                System.out.println("no treasure");
            }
        }
    }

    private void checkWinningCondition() {
        int destinationSize = 0;
        for (int i = 0; i < GameWindow.WIDTH / GameField.SIZEBLOCK; i++) {
            for (int j = 0; j < GameWindow.HEIGHT / GameField.SIZEBLOCK; j++) {
                /*
                CHECK EVERY TILE ==> IS TILE FREE
                --> IF EVERY TILE IS BLOCKED BY THE PLAYER OR A WALL THAN THE GAME IS WON
                 */
            }

        }
    }

    /**
     * updates the position of food and the treasure-box on the field.
     * if food and treasure are on the same tile, a new position of the food will be generated.
     */
    private void updateGameState() {
        if (!ObjectManager.getInstance().getFoodExisting()) {
            ObjectManager.getInstance().setFoodExisting(true);
            ObjectManager.getInstance().createFood();
        }
        spawnTreasure();
        for (int i = 1; i < Snake.getInstance().getPositions().size() - 1; i++) {
            if (Snake.getInstance().getPositions().get(i).getX() == ObjectManager.getInstance().getCurrentFood().getPosition().getX() &&
                    Snake.getInstance().getPositions().get(i).getY() == ObjectManager.getInstance().getCurrentFood().getPosition().getY()) {
                ObjectManager.getInstance().createFood();
            }
        }
        if (ObjectManager.getInstance().getCurrenObject() != null && (ObjectManager.getInstance().getCurrenObject().getPosition().getX() == ObjectManager.getInstance().getCurrentFood().getPosition().getX() &&
                ObjectManager.getInstance().getCurrenObject().getPosition().getY() == ObjectManager.getInstance().getCurrentFood().getPosition().getY())) {

            ObjectManager.getInstance().createFood();
        }

        checkCollision();
    }

    public Timer getGameTick() {
        return gameTick;
    }

    public TimerTask getMoveSnake() {
        return moveSnake;
    }

    /**
     * uses key inputs to let the snake move and reset the game
     *
     * @param gameScene main scene of the game Snake
     */
    public void keyHandler(Scene gameScene) {
        gameScene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP -> {
                    Snake.getInstance().setDirectionEnum(Directions.UP);
                    keyInputLogger.log(Level.TRACE, "UP-Button pressed");
                }
                case DOWN -> {
                    Snake.getInstance().setDirectionEnum(Directions.DOWN);
                    keyInputLogger.log(Level.TRACE, "DOWN-Button pressed");
                }
                case LEFT -> {
                    Snake.getInstance().setDirectionEnum(Directions.LEFT);
                    keyInputLogger.log(Level.TRACE, "LEFT-Button pressed");
                }
                case RIGHT -> {
                    Snake.getInstance().setDirectionEnum(Directions.RIGHT);
                    keyInputLogger.log(Level.TRACE, "RIGHT-Button pressed");
                }
                case R -> {
                    if (Snake.getInstance().isGameOver()) {
                        gameReset();
                    }

                }
            }
        });
    }


    /**
     * checks if the player is on an eatable or obstacle object.
     * if the player is on one, it creates a new eatable object or if
     * it is a wall its game over.
     * in addition, there will be added points to the score if the object was eatable
     */
    public void checkCollision() {
        for (IWallStructure wallStructure: ObjectManager.getInstance().getWallStructures()) {
            for (Wall wall : wallStructure.getWalls()) {
                if (wall.isBlocked() && wall.getPosition().getX() == Snake.getInstance().getPositions().get(0).getX() &&
                        wall.getPosition().getY() == Snake.getInstance().getPositions().get(0).getY()){
                    Snake.getInstance().setGameOver(true);
                }
            }
        }
        if (ObjectManager.getInstance().getCurrentFood().getPosition().getX() <= Snake.getInstance().getPositions().get(0).getX() &&
                ObjectManager.getInstance().getCurrentFood().getPosition().getY() <= Snake.getInstance().getPositions().get(0).getY() &&
                ObjectManager.getInstance().getCurrentFood().getPosition().getX() + GameField.SIZEBLOCK >= Snake.getInstance().getPositions().get(0).getX() + GameField.SIZEBLOCK &&
                ObjectManager.getInstance().getCurrentFood().getPosition().getY() + GameField.SIZEBLOCK >= Snake.getInstance().getPositions().get(0).getY() + GameField.SIZEBLOCK) {
            ObjectManager.getInstance().createFood();
            Snake.getInstance().setGrowing(true);
            Score.getInstance().setScore(Score.getInstance().getScore() + Score.getInstance().getFoodPoint());

        } else if (ObjectManager.getInstance().getCurrenObject() != null && (ObjectManager.getInstance().getCurrenObject().getPosition().getX() <= Snake.getInstance().getPositions().get(0).getX() &&
                ObjectManager.getInstance().getCurrenObject().getPosition().getY() <= Snake.getInstance().getPositions().get(0).getY() &&
                ObjectManager.getInstance().getCurrenObject().getPosition().getX() + GameField.SIZEBLOCK >= Snake.getInstance().getPositions().get(0).getX() + GameField.SIZEBLOCK &&
                ObjectManager.getInstance().getCurrenObject().getPosition().getY() + GameField.SIZEBLOCK >= Snake.getInstance().getPositions().get(0).getY() + GameField.SIZEBLOCK)) {
            Score.getInstance().setScore(Score.getInstance().getScore() + Score.getInstance().getTreasurePoint());
            ObjectManager.getInstance().setCurrenObject(null);

        } else {
            Snake.getInstance().setGrowing(false);
        }
        Platform.runLater(() -> {
            UiManager.getInstance().getScoreField().setText(String.valueOf(Score.getInstance().getScore()));
        });
    }

    /**
     * resets the gamestate to the starting position
     */
    public void gameReset() {
        Snake.getInstance().reset();
        Score.getInstance().reset();
        ObjectManager.getInstance().createObstacle();
        ObjectManager.getInstance().createFood();
    }
}
