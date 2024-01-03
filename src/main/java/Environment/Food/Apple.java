package Environment.Food;

import Environment.AObject;
import Environment.GameField;
import Environment.Position;
import Management.Interface.GameWindow;
import javafx.scene.image.Image;


public class Apple extends AObject {

    public Apple(Position position) {
        super(position);
        setAppearance(new Image("file:src/main/java/Environment/Food/FoodImages/apple.png", GameWindow.getInstance().getSizeBlock(), GameWindow.getInstance().getSizeBlock(),true,true));
    }
}
