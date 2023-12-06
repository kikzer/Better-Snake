package GameField.Food;

import GameField.GameField;
import GameField.IObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public abstract class AFood implements IObject {
    private Image appearance;
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

    public Image getAppearance() {
        return appearance;
    }

    public void setAppearance(Image appearance) {
        this.appearance = appearance;
    }

    @Override
    public void show(GraphicsContext graphicsContext){
        graphicsContext.drawImage(appearance,x,y);
    }
}
