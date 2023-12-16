package Environment.Food;

import Environment.AObject;
import Environment.GameField;
import Environment.Position;
import javafx.scene.image.Image;

public class Banana extends AObject {

    public Banana(Position position) {
        super(position);
        setAppearance(new Image("file:src/main/java/Environment/FoodImages/banana.png", GameField.SIZEBLOCK, GameField.SIZEBLOCK,true,true));
    }
}
