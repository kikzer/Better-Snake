package GameField;

import GameField.Food.AFood;
import GameField.Food.FoodFactory;
import GameField.Food.FoodNames;
import Management.Interface.GameWindow;

import java.util.ArrayList;
import java.util.Random;

public class FoodManager {
    public IObject currentFood;
    private FoodNames[] foodNames = FoodNames.values();
    private Random rnd = new Random();

    private Boolean foodExisting = false;

    public void createFood(){
        currentFood=FoodFactory.createFood(randomFood(),randomCoordinate(),randomCoordinate());
    }

    private FoodNames randomFood(){
        return foodNames[0];
    }

    private Integer randomCoordinate(){
        return rnd.nextInt(GameWindow.WIDTH/GameField.SIZE_BLOCK)*GameField.SIZE_BLOCK;
    }

    public Boolean getFoodExisting() {
        return foodExisting;
    }

    public void setFoodExisting(Boolean foodExisting) {
        this.foodExisting = foodExisting;
    }
}
