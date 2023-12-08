package Management;

import Environment.Food.FoodFactory;
import Environment.Food.FoodNames;
import Environment.GameField;
import Environment.IObject;
import Environment.Position;
import Management.Interface.GameWindow;

import java.util.Random;

public class FoodManager {
    public IObject currentFood;
    private final FoodNames[] foodNames = FoodNames.values();
    private final Random rnd = new Random();

    private static FoodManager instance;
    private Boolean foodExisting = false;

    public static FoodManager getInstance(){
        if(instance == null){
            instance = new FoodManager();
        }
        return instance;
    }

    public void createFood(){
        currentFood=FoodFactory.createFood(randomFood(),new Position(randomCoordinate(),randomCoordinate()));
    }

    private FoodNames randomFood(){
        return foodNames[rnd.nextInt(foodNames.length-1)];
    }

    private Integer randomCoordinate(){
        return rnd.nextInt(GameWindow.WIDTH/ GameField.SIZEBLOCK)*GameField.SIZEBLOCK;
    }

    public Boolean getFoodExisting() {
        return foodExisting;
    }

    public void setFoodExisting(Boolean foodExisting) {
        this.foodExisting = foodExisting;
    }

    public IObject getCurrentFood() {
        return currentFood;
    }

    public void setCurrentFood(IObject currentFood) {
        this.currentFood = currentFood;
    }

    public FoodNames[] getFoodNames() {
        return foodNames;
    }

    public Random getRnd() {
        return rnd;
    }

    public static void setInstance(FoodManager instance) {
        FoodManager.instance = instance;
    }
}
