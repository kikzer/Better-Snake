package Management.SnakeManagement;

import Enviroment.GameField;
import Enviroment.Position;
import Management.Interface.GameWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Objects;

public class Snake {

    private final ArrayList<Position> positions = new ArrayList<Position>();


    //Pos 0 = up, Pos 1 = down, Pos 2 = right, Pos 3 = left
    private final int[] directionX = {0, 0, 1, -1};
    private final int[] directionY = {-1, 1, 0, 0};

    private int direction = 1;

    private boolean gameOver = false;


    private Directions directionEnum = Directions.UP;


    public Snake(final int startPositionX, final int startPositionY) {
        positions.add(new Position(startPositionX, startPositionY));
        for (int i = 1; i < 8; i++) {
            positions.add(new Position(startPositionX, startPositionY - (GameField.SIZE_BLOCK)));
        }

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


    public int getDirection() {
        return direction;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void selfDestroy() {
        if (gameOver) {
            if (positions.size() - 1 > 0) {
                positions.remove(positions.size() - 1);
            }
        } else {
            for (int i = 1; i < positions.size(); i++) {

                if ((Objects.equals(positions.get(0).getX(), positions.get(i).getX())) && (Objects.equals(positions.get(0).getY(), positions.get(i).getY()))) {
                    gameOver = true;
                    break;
                }
            }
        }

    }

    public void move() {

        if (!gameOver) {
            decideDirections();
            positions.add(0, new Position(positions.get(0).getX() + getDirectionX()[getDirections()] * GameField.SIZE_BLOCK,
                    positions.get(0).getY() + getDirectionY()[getDirections()] * GameField.SIZE_BLOCK));
            positions.remove(positions.size() - 1);
            checkBorder();
        }
    }

    private void checkBorder() {
        if (positions.get(0).getX() < 0) {
            positions.get(0).setX(GameWindow.WIDTH - GameField.SIZE_BLOCK);
        } else if (positions.get(0).getX() > GameWindow.WIDTH - GameField.SIZE_BLOCK) {
            positions.get(0).setX(0);
        } else if (positions.get(0).getY() > GameWindow.WIDTH - GameField.SIZE_BLOCK) {
            positions.get(0).setY(0);
        } else if (positions.get(0).getY() < 0) {
            positions.get(0).setY(GameWindow.WIDTH - GameField.SIZE_BLOCK);
        }
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.GREENYELLOW);
        positions.stream().forEach(position -> graphicsContext.fillRect(position.getX(), position.getY(), GameField.SIZE_BLOCK, GameField.SIZE_BLOCK));
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(positions.get(0).getX(),positions.get(0).getY(),GameField.SIZE_BLOCK,GameField.SIZE_BLOCK);

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
