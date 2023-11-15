package GameField.Food;

import Management.SnakeManagement.Snake;
import javafx.scene.paint.Color;

public class Kiwi extends AFood {

    public Kiwi(int x, int y, Snake snake) {
        super(x, y, snake);
        setColor(Color.GREENYELLOW);
    }
}
