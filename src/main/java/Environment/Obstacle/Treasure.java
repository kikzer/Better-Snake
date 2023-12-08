package Environment.Obstacle;

import Environment.Position;
import javafx.scene.canvas.GraphicsContext;

public class Treasure extends AObstacle {

    public Treasure(int x, int y) {
        super(x, y);
    }


    @Override
    public void show(GraphicsContext graphicsContext) {

    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void setPosition(Position position) {

    }
}
