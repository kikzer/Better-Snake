package Environment.Food;

import Environment.GameField;
import Environment.Position;
import javafx.scene.image.Image;

public class Banana extends AFood {

    public Banana(Position position) {
        super(position);
        setAppearance(new Image("file:src/main/java/Environment/FoodImages/banana.png", GameField.SIZE_BLOCK, GameField.SIZE_BLOCK,true,true));
    }
}
