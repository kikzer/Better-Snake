package Management.SnakeManagement;

import GameField.GameField;
import Management.Interface.GameWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Snake {

    private final ArrayList<Integer> xPositions = new ArrayList<>(), yPositions = new ArrayList<>();

    //Pos 0 = up, Pos 1 = down, Pos 2 = right, Pos 3 = left
    private final int[] directionX = {0, 0, 1, -1};
    private final int[] directionY = {-1, 1, 0, 0};

    private int direction = 1;


    private Directions directionEnum = Directions.UP;

    public Snake(final int startPositionX, final int startPositionY) {
        getXPositions().add(startPositionX);

        getYPositions().add(startPositionY);
    }

    private void decideDirections() {
        switch (getDirectionEnum()) {
            case UP -> {
                if (getDirections() != 1) {
                    setDirection(0);
                }
            }
            case DOWN -> {
                if (getDirections() != 0) {
                    setDirection(1);
                }
            }
            case LEFT -> {
                if (getDirections() != 2) {
                    setDirection(3);
                }
            }
            case RIGHT -> {
                if (getDirections() != 3) {
                    setDirection(2);
                }
            }
        }
    }

    public void move() {
        decideDirections();
        getXPositions().add(0, getXPositions().get(0) + getDirectionX()[getDirections()] * GameField.SIZE_BLOCK);
        getYPositions().add(0, getYPositions().get(0) + getDirectionY()[getDirections()] * GameField.SIZE_BLOCK);

        getXPositions().remove(getXPositions().size() - 1);
        getYPositions().remove(getYPositions().size() - 1);
        checkBorder();
    }

    private void checkBorder() {
        if (getXPositions().get(0) < 0) {
            getXPositions().set(0, GameWindow.WIDTH - GameField.SIZE_BLOCK);
        } else if (getXPositions().get(0) > GameWindow.WIDTH - GameField.SIZE_BLOCK) {
            getXPositions().set(0, 0);
        } else if (getYPositions().get(0) > GameWindow.WIDTH - GameField.SIZE_BLOCK) {
            getYPositions().set(0, 0);
        } else if (getYPositions().get(0) < 0) {
            getXPositions().set(0, GameWindow.WIDTH - GameField.SIZE_BLOCK);
        }
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.GREENYELLOW);
        for (int i = 0; i < getXPositions().size(); i++) {
            if (i == 0) {
                graphicsContext.setFill(Color.RED);
            } else {
                graphicsContext.setFill(Color.GREENYELLOW);
            }
            graphicsContext.fillRect(getXPositions().get(i), getYPositions().get(i), GameField.SIZE_BLOCK, GameField.SIZE_BLOCK);
        }
    }

    public Directions getDirectionEnum() {
        return directionEnum;
    }

    public void setDirectionEnum(Directions directions) {
        directionEnum = directions;
    }

    public ArrayList<Integer> getXPositions() {
        return xPositions;
    }

    public int[] getDirectionX() {
        return directionX;
    }

    public int[] getDirectionY() {
        return directionY;
    }

    public ArrayList<Integer> getYPositions() {
        return yPositions;
    }

    public int getDirections() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
