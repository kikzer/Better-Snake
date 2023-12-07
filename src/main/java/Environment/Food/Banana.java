package Environment.Food;

import Environment.GameField;
import javafx.scene.image.Image;

public class Banana extends AFood {

    public Banana(int x, int y) {
        super(x, y);
        setAppearance(new Image("file:src/main/java/Environment/FoodImages/banana.png", GameField.SIZE_BLOCK, GameField.SIZE_BLOCK,true,true));
    }
}
