package Environment.Images;

import Environment.Food.*;
import Exceptions.InvalidFoodTypeException;
import Management.MetaDataHelper;
import javafx.scene.image.Image;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImageFactory {

    private static final Logger imageFactoryLogger = LogManager.getLogger(FoodFactory.class);
    private static final Image[] foodImages = {
            new Image("file:src/main/java/Environment/Images/FoodImages/cherry.png", MetaDataHelper.SIZEBLOCK, MetaDataHelper.SIZEBLOCK,true,true),
            new Image("file:src/main/java/Environment/Images/FoodImages/kiwi.png", MetaDataHelper.SIZEBLOCK, MetaDataHelper.SIZEBLOCK,true,true),
            new Image("file:src/main/java/Environment/Images/FoodImages/apple.png", MetaDataHelper.SIZEBLOCK, MetaDataHelper.SIZEBLOCK,true,true),
            new Image("file:src/main/java/Environment/Images/FoodImages/melon.png", MetaDataHelper.SIZEBLOCK, MetaDataHelper.SIZEBLOCK,true,true),
            new Image("file:src/main/java/Environment/Images/FoodImages/banana.png", MetaDataHelper.SIZEBLOCK,MetaDataHelper.SIZEBLOCK,true,true)
    };
    public static final Image[] obstacleImages = {
            new Image("file:src/main/java/Environment/Images/ObstacleImages/box.png", MetaDataHelper.SIZEBLOCK, MetaDataHelper.SIZEBLOCK,true,true),
            new Image("file:src/main/java/Environment/Images/ObstacleImages/wallBlock.png", MetaDataHelper.SIZEBLOCK, MetaDataHelper.SIZEBLOCK,true,true)
    };

    public static Image createFoodImage(FoodNames foodType){
        switch(foodType){
            case CHERRY -> {
                imageFactoryLogger.log(Level.DEBUG, "Creating CHERRY Image");
                return foodImages[0];
            }
            case KIWI -> {
                imageFactoryLogger.log(Level.DEBUG, "Creating KIWI Image");
                return foodImages[1];
            }
            case APPLE -> {
                imageFactoryLogger.log(Level.DEBUG, "Creating APPLE Image");
                return foodImages[2];
            }
            case MELON -> {
                imageFactoryLogger.log(Level.DEBUG, "Creating MELON Image");
                return foodImages[3];
            }
            case BANANA -> {
                imageFactoryLogger.log(Level.DEBUG, "Creating BANANA Image");
                return foodImages[4];
            }
            default ->{
                throw new InvalidFoodTypeException(String.valueOf(foodType));
            }
        }
    }
}
