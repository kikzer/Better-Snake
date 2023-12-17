package Management;

import Environment.Food.FoodFactory;
import Environment.Food.FoodNames;
import Environment.GameField;
import Environment.IObject;
import Environment.Obstacle.Treasure;
import Environment.Position;
import Management.Interface.GameWindow;
import Management.SnakeManagement.Snake;

import java.util.Random;

/**
 * manages the creation of the different food and the treasure by using random coordinates and
 * selectors which decide randomly
 */
public class ObjectManager {
    public IObject currentFood;
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

    /**
     * creates a random Food object using random coordinates
     */
    public void createFood() {
        currentFood = FoodFactory.createFood(randomFood(), randomCoordinate());
    }

    /**
     * picks a random food in the enum class
     * @return Enum type of food
     */
    private FoodNames randomFood() {
        return foodNames[rnd.nextInt(foodNames.length - 1)];
    }

    /**
     * generates a random Position object within the gamefield and checks if it is on the current player position.
     * if so, it creates new coordinates in 2D
     * @return Position object with random coordinates
     */
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

    /**
     * creates a treasure on a random tile
     */
    public void createObstacle() {
        currenObject = new Treasure(randomCoordinate());
    }

    public void setCurrenObject(IObject currenObject) {
        this.currenObject = currenObject;
    }

    public IObject getCurrenObject() {
        return currenObject;
    }
}
