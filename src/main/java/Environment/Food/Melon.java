package Environment.Food;

import Environment.AObject;
import Environment.GameField;
import Environment.Position;
import javafx.scene.image.Image;

public class Melon extends AObject {

    public Melon(Position position) {
        super(position);
        setAppearance(new Image("file:src/main/java/Environment/Food/FoodImages/melon.png", GameField.SIZEBLOCK, GameField.SIZEBLOCK,true,true));
    }
}
