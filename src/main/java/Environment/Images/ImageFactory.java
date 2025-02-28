package Environment.Images;

import Environment.Food.*;
import Exceptions.InvalidFoodTypeException;
import Management.MetaDataHelper;
import javafx.scene.image.Image;

public class ImageFactory {

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
                return foodImages[0];
            }
            case KIWI -> {
                return foodImages[1];
            }
            case APPLE -> {
                return foodImages[2];
            }
            case MELON -> {
                return foodImages[3];
            }
            case BANANA -> {
                return foodImages[4];
            }
            default ->{
                throw new InvalidFoodTypeException(String.valueOf(foodType));
            }
        }
    }
}
