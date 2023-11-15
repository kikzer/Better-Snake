package GameField;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface IObjectManager {
    default boolean checkCollision() {
        return false;
    }

    public void show(GraphicsContext graphicsContext, Color color);

}

