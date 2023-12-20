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
import Management.SnakeManagement.Snake;

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
        currentFood = FoodFactory.createFood(randomFood(), randomCoordinate());
    }

    public void createWallStructures(){
        for(int i = 0; i < 4; i++){
            wallStructures[i] = ObstacleFactory.createObstacle(i+1);
        }
    }

    private FoodNames randomFood() {
        return foodNames[rnd.nextInt(foodNames.length - 1)];
    }

    private Position randomCoordinate() {
        Position coordinate = new Position(rnd.nextInt(GameWindow.WIDTH / GameField.SIZEBLOCK) * GameField.SIZEBLOCK,
                rnd.nextInt(GameWindow.WIDTH / GameField.SIZEBLOCK) * GameField.SIZEBLOCK);
        for ( Position position : Snake.getInstance().getPositions()) {
            if(position.equals(coordinate)){
                randomCoordinate();
                break;
            }
        }
        return coordinate;
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
        currenObject = new Treasure(randomCoordinate());
    }

    public IObject getCurrenObject() {
        return currenObject;
    }

    public IWallStructure[] getWallStructures() {
        return wallStructures;
    }

    public void setCurrenObject(IObject currenObject) {
        this.currenObject=currenObject;
    }
}
