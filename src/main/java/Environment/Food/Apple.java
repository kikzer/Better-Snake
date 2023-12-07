package Environment.Food;

import Environment.GameField;
import Environment.Position;
import javafx.scene.image.Image;


public class Apple extends AFood {

    public Apple(Position position) {
        super(position);
        setAppearance(new Image("file:src/main/java/Environment/FoodImages/apple.png", GameField.SIZE_BLOCK, GameField.SIZE_BLOCK,true,true));
    }
}
