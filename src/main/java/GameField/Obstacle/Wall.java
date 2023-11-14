package GameField.Obstacle;

import Management.SnakeManagement.Snake;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Wall extends AObstacle {

    public Wall(int x, int y, Snake snake) {
        super(x, y, snake);
    }

    @Override
    public void show(GraphicsContext graphicsContext, Color color) {

    }
}
