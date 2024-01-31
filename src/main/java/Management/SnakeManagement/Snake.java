package Management.SnakeManagement;

import Environment.Position;
import Management.MetaDataHelper;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

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

    private final Position startPosition = new Position(MetaDataHelper.SIZEBLOCK, MetaDataHelper.SIZEBLOCK);

    private Directions directionEnum = Directions.DOWN;

    private static Snake instance;


    private static final Logger snakeLogger = LogManager.getLogger(Snake.class);

    private Snake() {
        positions.add(startPosition);
        positions.add(new Position(startPosition.getX(), startPosition.getY() - MetaDataHelper.SIZEBLOCK));
    }

    /**
     * resets the snake to it's starting position and condition
     */
    public void reset() {
        direction = 1;
        directionEnum = Directions.DOWN;
        positions.clear();
        positions.add(startPosition);
        positions.add(new Position(startPosition.getX(), startPosition.getY() - MetaDataHelper.SIZEBLOCK));
        gameOver = false;
        snakeLogger.log(Level.DEBUG, "Snake reseted");
    }

    public static Snake getInstance() {
        if (instance == null) {
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
                    snakeLogger.log(Level.TRACE, "Direction changed to UP");
                }
            }
            case DOWN -> {
                if (getDirections() != 0) {
                    setDirection(1);
                    snakeLogger.log(Level.TRACE, "Direction changed to DOWN");
                }
            }
            case LEFT -> {
                if (getDirections() != 2) {
                    setDirection(3);
                    snakeLogger.log(Level.TRACE, "Direction changed to LEFT");
                }
            }
            case RIGHT -> {
                if (getDirections() != 3) {
                    setDirection(2);
                    snakeLogger.log(Level.TRACE, "Direction changed to RIGHT");
                }
            }
        }
    }


    public boolean isGrowing() {
        return growing;
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
            positions.add(0, new Position(positions.get(0).getX() + getDirectionX()[getDirections()] * MetaDataHelper.SIZEBLOCK,
                    positions.get(0).getY() + getDirectionY()[getDirections()] * MetaDataHelper.SIZEBLOCK));

            if (!growing) {
                positions.remove(positions.size() - 1);
            }
            checkBorder();
        }
    }

    public void setGrowing(boolean growing) {
        this.growing = growing;
        if (growing) {
            snakeLogger.log(Level.DEBUG, "Snake growed");
        }
    }

    /**
     * if the snake moves out of the border, it gets teleported on the other side where it went out
     */
    private void checkBorder() {
        if (positions.get(0).getX() < 0) {
            positions.get(0).setX(MetaDataHelper.WIDTH - MetaDataHelper.SIZEBLOCK);
        } else if (positions.get(0).getX() > MetaDataHelper.WIDTH - MetaDataHelper.SIZEBLOCK) {
            positions.get(0).setX(0);
        } else if (positions.get(0).getY() > MetaDataHelper.HEIGHT - MetaDataHelper.SIZEBLOCK) {
            positions.get(0).setY(0);
        } else if (positions.get(0).getY() < 0) {
            positions.get(0).setY(MetaDataHelper.HEIGHT - MetaDataHelper.SIZEBLOCK);
        }
    }

    public List<Position> getPositions() {
        List<Position> copyPositions = positions;
        return copyPositions;
    }


    public Directions getDirectionEnum() {
        Directions copyDirectionEnum = directionEnum;
        return copyDirectionEnum;
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
