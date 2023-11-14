package GameField;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface IObjectManager {
    default boolean checkCollision() {
        return false;
    }

    void show(GraphicsContext graphicsContext, Color color);
}

