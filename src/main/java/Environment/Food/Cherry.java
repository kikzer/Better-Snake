package Environment.Food;

import Environment.GameField;
import Environment.Position;
import javafx.scene.image.Image;

public class Cherry extends AFood {

    public Cherry(Position position) {
        super(position);
        setAppearance(new Image("file:src/main/java/Environment/FoodImages/cherry.png", GameField.SIZE_BLOCK, GameField.SIZE_BLOCK,true,true));
    }
}
