package Environment.Food;

import Environment.AObject;
import Environment.GameField;
import Environment.Position;
import javafx.scene.image.Image;

public class Kiwi extends AObject {

    public Kiwi(Position position) {
        super(position);
        setAppearance(new Image("file:src/main/java/Environment/FoodImages/kiwi.png", GameField.SIZEBLOCK, GameField.SIZEBLOCK,true,true));
    }
}
