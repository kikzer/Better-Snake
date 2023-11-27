package GameField.Food;

import GameField.GameField;
import Management.SnakeManagement.Snake;
import javafx.scene.image.Image;

class Kiwi extends AFood {
    Image appearance = new Image("src/main/java/GameField/FoodImages/kiwi.png", GameField.SIZE_BLOCK, GameField.SIZE_BLOCK, false , false);

    public Kiwi(int x, int y) {
        super(x, y);
    }
}
