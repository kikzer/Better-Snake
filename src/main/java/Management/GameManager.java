package Management;

import Environment.GameField;
import Environment.IWallStructure;
import Environment.Obstacle.Wall;
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
import java.util.Timer;
import java.util.TimerTask;

public class GameManager {
    private static GameManager instance;

    private static final Logger gameManagerLogger = LogManager.getLogger(GameManager.class);

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
            gameManagerLogger.log(Level.DEBUG, "GameManager instance created");
            instance = new GameManager();
        }
        return instance;
    }

    /**
     * spawns a Treasure-box every 15 points on a random tile of the game field
     */
    private void spawnTreasure() {
        if (ObjectManager.getInstance().getCurrenTreasure() == null && !ObjectManager.getInstance().getTreasureExisting()) {
            ObjectManager.getInstance().setTreasureExisting(true);
            ObjectManager.getInstance().createTreasure();
            gameManagerLogger.log(Level.DEBUG, "treasure spawned");
        } else {
            if (Score.getInstance().getScore() % 15 == 0 && ObjectManager.getInstance().getCurrenTreasure() == null) {
                ObjectManager.getInstance().setTreasureExisting(false);
            }
        }
    }

    /**
     * spawns Food if the previous food has been eaten
     */
    private void spawnFood(){
        if (!ObjectManager.getInstance().getFoodExisting()) {
            ObjectManager.getInstance().setFoodExisting(true);
            ObjectManager.getInstance().createFood();
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
        spawnFood();
        spawnTreasure();

        if (ObjectManager.getInstance().getCurrenTreasure() != null && (ObjectManager.getInstance().getCurrenTreasure().getPosition().getX() == ObjectManager.getInstance().getCurrentFood().getPosition().getX() &&
                ObjectManager.getInstance().getCurrenTreasure().getPosition().getY() == ObjectManager.getInstance().getCurrentFood().getPosition().getY())) {

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
                    gameManagerLogger.log(Level.TRACE, "UP-Button pressed");
                }
                case DOWN -> {
                    Snake.getInstance().setDirectionEnum(Directions.DOWN);
                    gameManagerLogger.log(Level.TRACE, "DOWN-Button pressed");
                }
                case LEFT -> {
                    Snake.getInstance().setDirectionEnum(Directions.LEFT);
                    gameManagerLogger.log(Level.TRACE, "LEFT-Button pressed");
                }
                case RIGHT -> {
                    Snake.getInstance().setDirectionEnum(Directions.RIGHT);
                    gameManagerLogger.log(Level.TRACE, "RIGHT-Button pressed");
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
                        wall.getPosition().getY() == Snake.getInstance().getPositions().get(0).getY()&&!Snake.getInstance().isGameOver()){
                    Snake.getInstance().setGameOver(true);
                }
            }
        }
        if (ObjectManager.getInstance().getCurrentFood().getPosition().getX() <= Snake.getInstance().getPositions().get(0).getX() &&
                ObjectManager.getInstance().getCurrentFood().getPosition().getY() <= Snake.getInstance().getPositions().get(0).getY() &&
                ObjectManager.getInstance().getCurrentFood().getPosition().getX() + GameField.SIZEBLOCK >= Snake.getInstance().getPositions().get(0).getX() + GameField.SIZEBLOCK &&
                ObjectManager.getInstance().getCurrentFood().getPosition().getY() + GameField.SIZEBLOCK >= Snake.getInstance().getPositions().get(0).getY() + GameField.SIZEBLOCK) {
            gameManagerLogger.log(Level.DEBUG, "Food eaten");
            ObjectManager.getInstance().createFood();
            Snake.getInstance().setGrowing(true);
            Score.getInstance().setScore(Score.getInstance().getScore() + Score.getInstance().getFoodPoint());

        } else if (ObjectManager.getInstance().getCurrenTreasure() != null && (ObjectManager.getInstance().getCurrenTreasure().getPosition().getX() <= Snake.getInstance().getPositions().get(0).getX() &&
                ObjectManager.getInstance().getCurrenTreasure().getPosition().getY() <= Snake.getInstance().getPositions().get(0).getY() &&
                ObjectManager.getInstance().getCurrenTreasure().getPosition().getX() + GameField.SIZEBLOCK >= Snake.getInstance().getPositions().get(0).getX() + GameField.SIZEBLOCK &&
                ObjectManager.getInstance().getCurrenTreasure().getPosition().getY() + GameField.SIZEBLOCK >= Snake.getInstance().getPositions().get(0).getY() + GameField.SIZEBLOCK)) {
            gameManagerLogger.log(Level.DEBUG, "Treasure opened");
            Score.getInstance().setScore(Score.getInstance().getScore() + Score.getInstance().getTreasurePoint());
            ObjectManager.getInstance().setCurrenTreasure(null);

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
        ObjectManager.getInstance().createTreasure();
        ObjectManager.getInstance().createFood();
        gameManagerLogger.log(Level.DEBUG, "Game reseted");
    }
}
