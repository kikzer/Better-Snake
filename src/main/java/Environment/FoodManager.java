package Environment;

import Environment.Food.FoodFactory;
import Environment.Food.FoodNames;
import Management.Interface.GameWindow;

import java.util.Random;

public class FoodManager {
    public IObject currentFood;
    private final FoodNames[] foodNames = FoodNames.values();
    private final Random rnd = new Random();

    private Boolean foodExisting = false;

    public void createFood(){
        currentFood=FoodFactory.createFood(randomFood(),new Position(randomCoordinate(),randomCoordinate()));
    }

    private FoodNames randomFood(){
        return foodNames[rnd.nextInt(foodNames.length-1)];
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
