package GameField;

import GameField.Food.AFood;
import GameField.Food.FoodFactory;
import GameField.Food.FoodNames;

import java.util.ArrayList;
import java.util.Random;

public class FoodManager {
    public AFood currentFood;
    private FoodNames[] foodNames = FoodNames.values();
    private Random rnd = new Random();

    private Boolean foodExisting = false;

    public void createFood(){
        currentFood=FoodFactory.createFood(randomFood(),randomCoordinate(),randomCoordinate());
    }

    private FoodNames randomFood(){
        return foodNames[rnd.nextInt(foodNames.length)];
    }

    private Integer randomCoordinate(){
        return rnd.nextInt(25)*GameField.SIZE_BLOCK;
    }

    public Boolean getFoodExisting() {
        return foodExisting;
    }

    public void setFoodExisting(Boolean foodExisting) {
        this.foodExisting = foodExisting;
    }
}
