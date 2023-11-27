package GameField.Food;

import GameField.GameField;
import Management.SnakeManagement.Snake;
import javafx.scene.image.Image;

class Apple extends AFood {
    Image appearance = new Image("src/main/java/GameField/FoodImages/apple.png", GameField.SIZE_BLOCK, GameField.SIZE_BLOCK, false , false);
    public Apple(int x, int y) {
        super(x, y);
    }
}
