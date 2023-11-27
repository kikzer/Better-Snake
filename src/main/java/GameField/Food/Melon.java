package GameField.Food;

import GameField.GameField;
import Management.SnakeManagement.Snake;
import javafx.scene.image.Image;

class Melon extends AFood{
    Image appearance = new Image("src/main/java/GameField/FoodImages/melon.png", GameField.SIZE_BLOCK, GameField.SIZE_BLOCK, false , false);

    public Melon(int x, int y) {
        super(x, y);
    }
}
