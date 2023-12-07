package Enviroment.Food;

import Enviroment.GameField;
import javafx.scene.image.Image;


public class Apple extends AFood {

    public Apple(int x, int y) {
        super(x, y);
        setAppearance(new Image("file:src/main/java/GameField/FoodImages/apple.png", GameField.SIZE_BLOCK, GameField.SIZE_BLOCK,true,true));
    }
}
