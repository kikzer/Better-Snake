package Environment;

import javafx.scene.canvas.GraphicsContext;

public interface IObject {
    void show(GraphicsContext graphicsContext);

    Position getPosition();

    void setPosition(Position position);

}

