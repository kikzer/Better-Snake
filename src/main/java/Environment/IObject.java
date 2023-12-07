package Environment;

import javafx.scene.canvas.GraphicsContext;

public interface IObject {
    void show(GraphicsContext graphicsContext);

    int getX();
    void setX(int x);

    int getY();

    void setY(int y);
}

