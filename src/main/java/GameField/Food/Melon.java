package GameField.Food;

import Management.SnakeManagement.Snake;
import javafx.scene.paint.Color;

public class Melon extends AFood{

    public Melon(int x, int y, Snake snake) {
        super(x, y, snake);
        setColor(Color.LIGHTGREEN);
    }
}
