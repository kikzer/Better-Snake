package Environment.Obstacle;

import Environment.Position;
import javafx.scene.canvas.GraphicsContext;

public class Wall extends AObstacle {

    public Wall(int x, int y) {
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
