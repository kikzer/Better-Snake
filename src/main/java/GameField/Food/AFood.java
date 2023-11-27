package GameField.Food;

import GameField.GameField;
import GameField.IObject;
import Management.SnakeManagement.Snake;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AFood implements IObject {

    private int x,y;

    public AFood(final int x, final int y){
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

    @Override
    public void show(GraphicsContext graphicsContext, Color color){
        graphicsContext.setFill(color);
        graphicsContext.fillRect(getX()*GameField.SIZE_BLOCK,getY()*GameField.SIZE_BLOCK, GameField.SIZE_BLOCK,GameField.SIZE_BLOCK);
    }
}
