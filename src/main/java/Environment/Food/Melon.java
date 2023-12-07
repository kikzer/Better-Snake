package Environment.Food;

import Environment.GameField;
import javafx.scene.image.Image;

public class Melon extends AFood{

    public Melon(int x, int y) {
        super(x, y);
        setAppearance(new Image("file:src/main/java/Environment/FoodImages/melon.png", GameField.SIZE_BLOCK, GameField.SIZE_BLOCK,true,true));
    }
}
