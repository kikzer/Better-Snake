package GameField.Food;

import Management.SnakeManagement.Snake;
import javafx.scene.paint.Color;

public class Cherry extends AFood {

    public Cherry(int x, int y) {
        super(x, y);
        setColor(Color.DARKRED);
    }
}
