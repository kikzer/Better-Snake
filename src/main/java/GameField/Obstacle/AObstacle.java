package GameField.Obstacle;

import GameField.ObjectManager;
import Management.SnakeManagement.Snake;

public abstract class AObstacle implements ObjectManager {

    private int x,y;
    private final Snake snake;

    public AObstacle(final int x, final int y, final Snake snake){
        this.snake = snake;
        this.x = x;
        this.y = y;
    }

    public boolean checkCollision(){
        return getSnake().getXPositions().get(0) == getX() && getSnake().getYPositions().get(0) == getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Snake getSnake() {
        return snake;
    }
}
