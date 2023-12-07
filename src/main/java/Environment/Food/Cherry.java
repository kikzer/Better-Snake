package Environment.Food;

import Environment.GameField;
import javafx.scene.image.Image;

public class Cherry extends AFood {

    public Cherry(int x, int y) {
        super(x, y);
        setAppearance(new Image("file:src/main/java/Environment/FoodImages/cherry.png", GameField.SIZE_BLOCK, GameField.SIZE_BLOCK,true,true));
    }
}
