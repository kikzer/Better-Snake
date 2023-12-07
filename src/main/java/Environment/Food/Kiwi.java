package Environment.Food;

import Environment.GameField;
import javafx.scene.image.Image;

public class Kiwi extends AFood {

    public Kiwi(int x, int y) {
        super(x, y);
        setAppearance(new Image("file:src/main/java/Environment/FoodImages/kiwi.png", GameField.SIZE_BLOCK, GameField.SIZE_BLOCK,true,true));
    }
}
