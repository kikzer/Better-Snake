package GameField.Obstacle;

import GameField.IObject;
import Management.SnakeManagement.Snake;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AObstacle implements IObject {

    private int x,y;

    public AObstacle(final int x, final int y){
        this.x = x;
        this.y = y;
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

    public abstract void show(GraphicsContext graphicsContext);
}
