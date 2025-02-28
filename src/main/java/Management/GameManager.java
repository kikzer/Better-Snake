package Management;


import Environment.Obstacle.IShape;
import Environment.Obstacle.ObstacleForms.Wall;
import Management.Interface.GameWindow;
import Management.Interface.Score;
import Management.Interface.UiManager;
import Management.SnakeManagement.Directions;
import Management.SnakeManagement.Snake;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Main manager class of the project. It handles most of the games logic including the gametick.
 * It updates the gamestate, tells other manager classes what to do and is responsible for the player inputs.
 */
public class GameManager {
    private static GameManager instance;

    private static final Logger gameManagerLogger = LogManager.getLogger(GameManager.class);

    private boolean switchScene = false;

    private boolean gameWon = true;
    public Timer gameTick = new Timer();

    private int gameSpeed = 200;

    private int winningCondition = 0;

    /**
     * Tick system which makes it possible to have a working game flow using Runnable
     */
    TimerTask moveSnake = new TimerTask() {
        @Override
        public void run() {

            if (gameWon || (!Snake.getInstance().isGameOver())) {
                Snake.getInstance().move();
                updateGameState();
                UiManager.getInstance().updateGameField();
                keyHandler(GameWindow.getInstance().getGameScene());
                Snake.getInstance().selfDestroy();
            }
        }
    };

    private GameManager() {
        gameTick.schedule(moveSnake, 0, gameSpeed);
        createWinningCondition();
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
    private void spawnFood() {
        if (!ObjectManager.getInstance().getFoodExisting()) {
            ObjectManager.getInstance().setFoodExisting(true);
            ObjectManager.getInstance().createFood();
        }
    }

    public static void setInstance(GameManager instance) {
        GameManager.instance = instance;
    }

    /**
     * Setting winningCondition depending on the size of the gamefield.
     */
    private void createWinningCondition() {
        int wallAmount = 0;
        for (IShape structure : ObjectManager.getInstance().getWallStructures()) {
            if (structure != null)
                wallAmount += structure.getWalls().size();
        }
        winningCondition = (MetaDataHelper.SIZEBLOCK * MetaDataHelper.SIZEBLOCK) - wallAmount - MetaDataHelper.SIZEBLOCK;
    }

    /**
     * Checks if winnning condition is met or not.
     */
    private void checkWinningCondition() {
        if (winningCondition == Snake.getInstance().getPositions().size()) {
            gameWon = true;
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
        checkWinningCondition();
    }

    public Timer getGameTick() {
        Timer copyGameTick = gameTick;
        return copyGameTick;
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
        for (IShape wallStructure : ObjectManager.getInstance().getWallStructures()) {
            for (Wall wall : wallStructure.getWalls()) {
                if (wall.isBlocked() && wall.getPosition().getX() == Snake.getInstance().getPositions().get(0).getX() &&
                        wall.getPosition().getY() == Snake.getInstance().getPositions().get(0).getY() && !Snake.getInstance().isGameOver()) {
                    Snake.getInstance().setGameOver(true);
                }
            }
        }
        for (int i = 1; i < Snake.getInstance().getPositions().size(); i++) {

            if ((Objects.equals(Snake.getInstance().getPositions().get(0).getX(), Snake.getInstance().getPositions().get(i).getX())) &&
                    (Objects.equals(Snake.getInstance().getPositions().get(0).getY(), Snake.getInstance().getPositions().get(i).getY())) && !Snake.getInstance().isGameOver()) {
                Snake.getInstance().setGameOver(true);
                break;
            }
        }
        if (ObjectManager.getInstance().getCurrentFood().getPosition().getX() <= Snake.getInstance().getPositions().get(0).getX() &&
                ObjectManager.getInstance().getCurrentFood().getPosition().getY() <= Snake.getInstance().getPositions().get(0).getY() &&
                ObjectManager.getInstance().getCurrentFood().getPosition().getX() + MetaDataHelper.SIZEBLOCK >= Snake.getInstance().getPositions().get(0).getX() + MetaDataHelper.SIZEBLOCK &&
                ObjectManager.getInstance().getCurrentFood().getPosition().getY() + MetaDataHelper.SIZEBLOCK >= Snake.getInstance().getPositions().get(0).getY() + MetaDataHelper.SIZEBLOCK) {
            gameManagerLogger.log(Level.DEBUG, "Food eaten");
            ObjectManager.getInstance().createFood();
            Snake.getInstance().setGrowing(true);
            Score.getInstance().setScore(Score.getInstance().getScore() + Score.getInstance().getFoodPoint());

        } else if (ObjectManager.getInstance().getCurrenTreasure() != null && (ObjectManager.getInstance().getCurrenTreasure().getPosition().getX() <= Snake.getInstance().getPositions().get(0).getX() &&
                ObjectManager.getInstance().getCurrenTreasure().getPosition().getY() <= Snake.getInstance().getPositions().get(0).getY() &&
                ObjectManager.getInstance().getCurrenTreasure().getPosition().getX() + MetaDataHelper.SIZEBLOCK >= Snake.getInstance().getPositions().get(0).getX() + MetaDataHelper.SIZEBLOCK &&
                ObjectManager.getInstance().getCurrenTreasure().getPosition().getY() + MetaDataHelper.SIZEBLOCK >= Snake.getInstance().getPositions().get(0).getY() + MetaDataHelper.SIZEBLOCK)) {
            gameManagerLogger.log(Level.DEBUG, "Treasure opened");
            Score.getInstance().setScore(Score.getInstance().getScore() + Score.getInstance().getTreasurePoint());
            ObjectManager.getInstance().setCurrenTreasure(null);

        } else {
            Snake.getInstance().setGrowing(false);
        }
        if (Platform.isFxApplicationThread()) {
            Platform.runLater(() -> {
                if (Score.getInstance().getHighScore() <= Score.getInstance().getScore()) {
                    Score.getInstance().setHighScore(Score.getInstance().getScore());
                }
                UiManager.getInstance().getScoreField().setText("Score: " + Score.getInstance().getScore());
                UiManager.getInstance().getHighScoreField().setText("HighScore: " + Score.getInstance().getHighScore());
            });

        }
        if (Snake.getInstance().isGameOver() && !switchScene && Snake.getInstance().getPositions().size() == 1) {
            Score.getInstance().updateHighScoreFile();
            switchScene = true;
            Platform.runLater(() -> {
                try {
                    Stage currentStage = (Stage) GameWindow.getInstance().getGameScene().getWindow();
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GameOverScene.fxml")));
                    Scene gameOverScene = new Scene(root);
                    currentStage.setScene(gameOverScene);
                    currentStage.show();
                } catch (Exception e) {
                    gameManagerLogger.log(Level.WARN, "Can't switch to Game Over Scene");
                    gameManagerLogger.log(Level.WARN, e.getMessage());
                }
            });

        }
    }

    /**
     * resets the gamestate to the starting position
     */
    public void gameReset() {
        Snake.getInstance().reset();
        Score.getInstance().reset();
        ObjectManager.getInstance().createTreasure();
        ObjectManager.getInstance().createFood();
        ObjectManager.getInstance().createWallStructures();
        createWinningCondition();
        gameSpeed = 200;
        gameManagerLogger.log(Level.DEBUG, "Game reseted");
        switchScene = false;
    }

    public boolean isGameWon() {
        return gameWon;
    }
}
