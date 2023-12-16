package Environment.Food;

import Environment.AObject;
import Environment.GameField;
import Environment.Position;
import javafx.scene.image.Image;


public class Apple extends AObject {

    public Apple(Position position) {
        super(position);
        setAppearance(new Image("file:src/main/java/Environment/FoodImages/apple.png", GameField.SIZEBLOCK, GameField.SIZEBLOCK,true,true));
    }
}
