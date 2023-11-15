package GameField.Food;

import Management.SnakeManagement.Snake;
import javafx.scene.paint.Color;

public class Banana extends AFood {

    public Banana(int x, int y) {
        super(x, y);
        setColor(Color.YELLOW);
    }
}
