package Environment.Food;

import Environment.AObject;
import Environment.GameField;
import Environment.Position;
import javafx.scene.image.Image;

public class Cherry extends AObject {

    public Cherry(Position position) {
        super(position);
        setAppearance(new Image("file:src/main/java/Environment/FoodImages/cherry.png", GameField.SIZEBLOCK, GameField.SIZEBLOCK,true,true));
    }
}
