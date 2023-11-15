package GameField.Food;

import Management.SnakeManagement.Snake;
import javafx.scene.paint.Color;

public class Apple extends AFood {

    public Apple(int x, int y, Snake snake) {
        super(x, y, snake);
        setColor(Color.GREEN);
    }
}
