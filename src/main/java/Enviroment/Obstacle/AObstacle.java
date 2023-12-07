package Enviroment.Obstacle;

import Enviroment.IObjectManager;
import Management.SnakeManagement.Snake;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AObstacle implements IObjectManager {

    private int x,y;

    private Color color;

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


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void draw(GraphicsContext graphicsContext);
}
