package Environment.Food;

import Environment.AObject;
import Environment.GameField;
import Environment.Position;
import Management.Interface.GameWindow;
import javafx.scene.image.Image;

public class Kiwi extends AObject {

    public Kiwi(Position position) {
        super(position);
        setAppearance(new Image("file:src/main/java/Environment/Food/FoodImages/kiwi.png", GameWindow.getInstance().getSizeBlock(), GameWindow.getInstance().getSizeBlock(),true,true));
    }
}
