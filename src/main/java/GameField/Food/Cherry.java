package GameField.Food;

import GameField.GameField;
import Management.SnakeManagement.Snake;
import javafx.scene.image.Image;

class Cherry extends AFood {
    Image appearance = new Image("src/main/java/GameField/FoodImages/cherrys.png", GameField.SIZE_BLOCK, GameField.SIZE_BLOCK, false , false);

    public Cherry(int x, int y) {
        super(x, y);
    }
}
