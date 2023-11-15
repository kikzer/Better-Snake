package GameField.Food;

import Management.SnakeManagement.Snake;
import javafx.scene.paint.Color;

public class Banana extends AFood {

    public Banana(int x, int y, Snake snake) {
        super(x, y, snake);
        setColor(Color.YELLOW);
    }
}
