package GameField.Food;

import GameField.GameField;
import Management.SnakeManagement.Snake;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class Cherry extends AFood {
    Image appearance;
    ImageView view;
    Rectangle2D viewport;

    public Cherry(int x, int y) {
        super(x, y);
        appearance = new Image("file:src/main/java/GameField/FoodImages/cherrys.png");
        view = new ImageView(appearance);
        viewport = new Rectangle2D(x,y,GameField.SIZE_BLOCK, GameField.SIZE_BLOCK);
        view.setViewport(viewport);
        System.out.println("Cherrys created"+x + " "+ y);
    }
}
