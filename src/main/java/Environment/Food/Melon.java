package Environment.Food;

import Environment.GameField;
import Environment.Position;
import javafx.scene.image.Image;

public class Melon extends AFood{

    public Melon(Position position) {
        super(position);
        setAppearance(new Image("file:src/main/java/Environment/FoodImages/melon.png", GameField.SIZE_BLOCK, GameField.SIZE_BLOCK,true,true));
    }
}
