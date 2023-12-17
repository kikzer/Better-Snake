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
        int storage = 0;
        if (ObjectManager.getInstance().getCurrenObject() == null && !ObjectManager.getInstance().getObstacleExisting()) {
            ObjectManager.getInstance().setObstacleExisting(true);
            ObjectManager.getInstance().createObstacle();
        }else{
            if(Score.getInstance().getScore() % 15 == 0 && ObjectManager.getInstance().getCurrenObject() == null){
                ObjectManager.getInstance().setObstacleExisting(false);
            }
        }
    }

    //TODO DO IT
    private void checkWinningCondition(){
        int destinationSize = 0;
        for (int i = 0; i < GameWindow.WIDTH/GameField.SIZEBLOCK; i++) {
            for (int j = 0; j < GameWindow.HEIGHT/GameField.SIZEBLOCK; j++) {
                /*
                CHECK EVERY TILE ==> IS TILE FREE
                --> IF EVERY TILE IS BLOCKED BY THE PLAYER OR A WALL THAN THE GAME IS WON
                 */
            }

        }
    }

    //TODO DO IT
    private void checkWinningCondition(){
        int destinationSize = 0;
        for (int i = 0; i < GameWindow.WIDTH/GameField.SIZEBLOCK; i++) {
            for (int j = 0; j < GameWindow.HEIGHT/GameField.SIZEBLOCK; j++) {
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
     * @param gameScene main scene of the game Snake
     */
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


    /**
     * checks if the player is on an eatable or obstacle object.
     * if the player is on one, it creates a new eatable object or if
     * it is a wall its game over.
     * in addition, there will be added points to the score if the object was eatable
     */
    public void checkCollision() {
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
            if (ObjectManager.getInstance().getCurrenObject().isBlocked()) {
                Snake.getInstance().setGameOver(true);
            } else {
                Score.getInstance().setScore(Score.getInstance().getScore() + Score.getInstance().getTreasurePoint());
                ObjectManager.getInstance().setCurrenObject(null);
            }

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
    }
}
