package Enviroment;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface IObjectManager {
    Color color = null;
    default boolean checkCollision() {
        return false;
    }

    public void draw(GraphicsContext graphicsContext);

}

