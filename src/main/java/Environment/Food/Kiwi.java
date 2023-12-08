package Environment.Food;

import Environment.GameField;
import Environment.Position;
import javafx.scene.image.Image;

public class Kiwi extends AFood {

    public Kiwi(Position position) {
        super(position);
        setAppearance(new Image("file:src/main/java/Environment/FoodImages/kiwi.png", GameField.SIZE_BLOCK, GameField.SIZE_BLOCK,true,true));
    }
}
