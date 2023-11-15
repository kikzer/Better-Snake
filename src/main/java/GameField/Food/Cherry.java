package GameField.Food;

import Management.SnakeManagement.Snake;
import javafx.scene.paint.Color;

public class Cherry extends AFood {

    public Cherry(int x, int y, Snake snake) {
        super(x, y, snake);
        setColor(Color.DARKRED);
    }
}
