package GameField.Food;

import GameField.GameField;
import GameField.IObjectManager;
import Management.SnakeManagement.Snake;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AFood implements IObjectManager {

    private int x, y;

    private Color color = Color.WHITE;

    public AFood(final int x, final int y) {
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

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(getColor());
        graphicsContext.fillRect(getX() * GameField.SIZE_BLOCK, getY() * GameField.SIZE_BLOCK, GameField.SIZE_BLOCK, GameField.SIZE_BLOCK);
    }
}
