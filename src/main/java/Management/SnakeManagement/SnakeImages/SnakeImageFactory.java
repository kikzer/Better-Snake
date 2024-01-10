package Management.SnakeManagement.SnakeImages;

import Environment.Food.FoodFactory;
import Exceptions.InvalidFoodTypeException;
import Management.MetaDataHelper;
import Management.SnakeManagement.Directions;
import javafx.scene.image.Image;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SnakeImageFactory {
    private static final Image[] snakeImages = {
            new Image("file:src/main/java/Management/SnakeManagement/SnakeImages/snakeHeadUp.png", MetaDataHelper.SIZEBLOCK, MetaDataHelper.SIZEBLOCK, true, true),
            new Image("file:src/main/java/Management/SnakeManagement/SnakeImages/snakeHeadDown.png", MetaDataHelper.SIZEBLOCK, MetaDataHelper.SIZEBLOCK, true, true),
            new Image("file:src/main/java/Management/SnakeManagement/SnakeImages/snakeHeadLeft.png", MetaDataHelper.SIZEBLOCK, MetaDataHelper.SIZEBLOCK, true, true),
            new Image("file:src/main/java/Management/SnakeManagement/SnakeImages/snakeHeadRight.png", MetaDataHelper.SIZEBLOCK, MetaDataHelper.SIZEBLOCK, true, true)
    };

    public static Image createSnakeHeadImage(int direction){
        switch(direction){
            case 0 -> {
                return snakeImages[0];
            }
            case 1 -> {
                return snakeImages[1];
            }
            case 3 -> {
                return snakeImages[2];
            }
            case 2 -> {
                return snakeImages[3];
            }
            default ->{
                throw new InvalidFoodTypeException(String.valueOf(direction));
            }
        }
    }
}
