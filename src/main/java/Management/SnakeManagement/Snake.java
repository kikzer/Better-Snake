package Management.SnakeManagement;

import Environment.GameField;
import Environment.Position;
import Management.GameManager;
import Management.Interface.GameWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * this class represents the player aka the snake, which is being used to play
 * the game better snake. the snake can move, kill itself, has a death animation when it's dead
 * and can't walk out of the game field because of a teleport function.
 */
public class Snake {

    private final List<Position> positions = new ArrayList<>();

    //Pos 0 = up, Pos 1 = down, Pos 2 = right, Pos 3 = left
    private final int[] directionX = {0, 0, 1, -1};
    private final int[] directionY = {-1, 1, 0, 0};

    private int direction = 1;

    private boolean gameOver = false;

    private boolean growing = false;

    private final Position startPosition = new Position(GameWindow.getInstance().getSizeBlock(), GameWindow.getInstance().getSizeBlock());

    private Directions directionEnum = Directions.UP;

    private static Snake instance;

    private Image snakeHead = new Image("file:src/main/java/Management/SnakeManagement/SnakeImages/snakeHeadDown.png", GameWindow.getInstance().getSizeBlock(), GameWindow.getInstance().getSizeBlock(),true,true);
    private final Image snakeBody = new Image("file:src/main/java/Management/SnakeManagement/SnakeImages/snakeBlock.png", GameWindow.getInstance().getSizeBlock(), GameWindow.getInstance().getSizeBlock(),true,true);

    private static final Logger snakeLogger = LogManager.getLogger(Snake.class);
    private Snake() {
        positions.add(startPosition);
        positions.add(new Position(startPosition.getX(),startPosition.getY()-GameWindow.getInstance().getSizeBlock()));
    }

    /**
     * resets the snake to it's starting position and condition
     */
    public void reset(){
        direction = 1;
        directionEnum = Directions.DOWN;
        positions.clear();
        positions.add(startPosition);
        positions.add(new Position(startPosition.getX(),startPosition.getY()-GameWindow.getInstance().getSizeBlock()));
        gameOver = false;
        snakeHead = new Image("file:src/main/java/Management/SnakeManagement/SnakeImages/snakeHeadDown.png", GameWindow.getInstance().getSizeBlock(), GameWindow.getInstance().getSizeBlock(),true,true);
        snakeLogger.log(Level.DEBUG, "Snake reseted");
    }

    public static Snake getInstance(){
        if (instance == null){
            instance = new Snake();
            snakeLogger.log(Level.DEBUG, "Snake instance created");
        }
        return instance;
    }

    /**
     * by using enum entries, the direction of the snake gets managed.
     * it's also impossible to do a 180, so that the snake instantly dies
     */
    private void decideDirections() {
        switch (getDirectionEnum()) {
            case UP -> {
                if (getDirections() != 1) {
                    setDirection(0);
                    snakeHead = new Image("file:src/main/java/Management/SnakeManagement/SnakeImages/snakeHeadUp.png", GameWindow.getInstance().getSizeBlock(), GameWindow.getInstance().getSizeBlock(),true,true);
                    snakeLogger.log(Level.TRACE, "Direction changed to UP");
                }
            }
            case DOWN -> {
                if (getDirections() != 0) {
                    setDirection(1);
                    snakeHead = new Image("file:src/main/java/Management/SnakeManagement/SnakeImages/snakeHeadDown.png", GameWindow.getInstance().getSizeBlock(), GameWindow.getInstance().getSizeBlock(),true,true);
                    snakeLogger.log(Level.TRACE, "Direction changed to DOWN");
                }
            }
            case LEFT -> {
                if (getDirections() != 2) {
                    setDirection(3);
                    snakeHead = new Image("file:src/main/java/Management/SnakeManagement/SnakeImages/snakeHeadLeft.png", GameWindow.getInstance().getSizeBlock(), GameWindow.getInstance().getSizeBlock(),true,true);
                    snakeLogger.log(Level.TRACE, "Direction changed to LEFT");
                }
            }
            case RIGHT -> {
                if (getDirections() != 3) {
                    setDirection(2);
                    snakeHead = new Image("file:src/main/java/Management/SnakeManagement/SnakeImages/snakeHeadRight.png", GameWindow.getInstance().getSizeBlock(), GameWindow.getInstance().getSizeBlock(),true,true);
                    snakeLogger.log(Level.TRACE, "Direction changed to RIGHT");
                }
            }
        }
    }

    public int getDirection() {
        return direction;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        snakeLogger.log(Level.DEBUG, "Snake died!");
    }

    /**
     * if the snake ate itself, it slowly gets deleted tick by tick from the tail to the head
     * (until the list is only one big)
     */
    public void selfDestroy() {
        if (gameOver) {
            if (positions.size() - 1 > 0) {
                positions.remove(positions.size() - 1);
            }
        }
    }

    /**
     * lets the snake move, if it isn't dead, by 25px per tick
     */
    public void move() {

        if (!gameOver) {
            decideDirections();
            positions.add(0, new Position(positions.get(0).getX() + getDirectionX()[getDirections()] * GameWindow.getInstance().getSizeBlock(),
                    positions.get(0).getY() + getDirectionY()[getDirections()] * GameWindow.getInstance().getSizeBlock()));

            if (!growing) {
                positions.remove(positions.size() - 1);
            }
            checkBorder();
        }
    }

    public void setGrowing(boolean growing) {
        this.growing = growing;
        if(growing){
            snakeLogger.log(Level.DEBUG, "Snake growed");
        }
    }

    /**
     * if the snake moves out of the border, it gets teleported on the other side where it went out
     */
    private void checkBorder() {
        if (positions.get(0).getX() < 0) {
            positions.get(0).setX(GameWindow.getInstance().getWidth() - GameWindow.getInstance().getSizeBlock());
        } else if (positions.get(0).getX() > GameWindow.getInstance().getWidth() - GameWindow.getInstance().getSizeBlock()) {
            positions.get(0).setX(0);
        } else if (positions.get(0).getY() > GameWindow.getInstance().getHeight() - GameWindow.getInstance().getSizeBlock()) {
            positions.get(0).setY(0);
        } else if (positions.get(0).getY() < 0) {
            positions.get(0).setY(GameWindow.getInstance().getHeight() - GameWindow.getInstance().getSizeBlock());
        }
    }

    public List<Position> getPositions() {
        return positions;
    }

    /**
     * draws the player. the head has the color red and the body the color greenyellow
     * @param graphicsContext lets the draw function draw and needs to be from the GameWindow class
     */
    public void draw(GraphicsContext graphicsContext) {
        positions.stream().skip(1).forEach(position -> graphicsContext.drawImage(snakeBody,position.getX(), position.getY()));
        graphicsContext.drawImage(snakeHead,positions.get(0).getX(), positions.get(0).getY());
    }

    public Directions getDirectionEnum() {
        return directionEnum;
    }

    public void setDirectionEnum(Directions directions) {
        directionEnum = directions;
    }


    public int[] getDirectionX() {
        return directionX;
    }

    public int[] getDirectionY() {
        return directionY;
    }


    public int getDirections() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }


}
