package Management;

import Environment.Food.FoodFactory;
import Environment.Food.FoodNames;
import Environment.GameField;
import Environment.IObject;
import Environment.IWallStructure;
import Environment.Obstacle.ObstacleFactory;
import Environment.Obstacle.Treasure;
import Environment.Position;
import Management.Interface.GameWindow;

import java.util.Random;

public class ObjectManager {
    public IObject currentFood;
    public IWallStructure[] wallStructures = new IWallStructure[4];
    private final FoodNames[] foodNames = FoodNames.values();
    private final Random rnd = new Random();

    public IObject currenObject;
    private Boolean obstacleExisting = false;



    private static ObjectManager instance;
    private Boolean foodExisting = false;

    public static ObjectManager getInstance() {
        if (instance == null) {
            instance = new ObjectManager();
        }
        return instance;
    }

    public void createFood() {
        currentFood = FoodFactory.createFood(randomFood(), new Position(randomCoordinate(), randomCoordinate()));
    }

    public void createWallStructures(){
        for(int i = 0; i < 4; i++){
            wallStructures[i] = ObstacleFactory.createObstacle(i+1);
        }
    }

    private FoodNames randomFood() {
        return foodNames[rnd.nextInt(foodNames.length - 1)];
    }

    private Integer randomCoordinate() {
        return rnd.nextInt(GameWindow.WIDTH / GameField.SIZEBLOCK) * GameField.SIZEBLOCK;
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

    public Boolean getObstacleExisting() {
        return obstacleExisting;
    }

    public void setObstacleExisting(Boolean obstacleExisting) {
        this.obstacleExisting = obstacleExisting;
    }

    public void createObstacle() {
        currenObject = new Treasure(new Position(randomCoordinate(), randomCoordinate()));
    }

    public IObject getCurrenObject() {
        return currenObject;
    }

    public IWallStructure[] getWallStructures() {
        return wallStructures;
    }
}
