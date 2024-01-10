package Environment.Food;

import Environment.AObject;
import Environment.GameField;
import Environment.Position;
import Management.Interface.GameWindow;
import Management.MetaDataHelper;
import javafx.scene.image.Image;

public class Melon extends AObject {

    public Melon(Position position) {
        super(position);
        setAppearance("file:src/main/java/Environment/Images/FoodImages/melon.png");
    }
}
