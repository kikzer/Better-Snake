package GameField.Food;

import GameField.GameField;
import Management.Interface.GameWindow;
import Management.SnakeManagement.Snake;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

class Apple extends AFood {
    public Apple(int x, int y) {
        super(x, y);
        setAppearance(new Image("file:src/main/java/GameField/FoodImages/apple.png", GameField.SIZE_BLOCK, GameField.SIZE_BLOCK,true,true));
        System.out.println("Apple created"+x + " "+ y);
    }
}
