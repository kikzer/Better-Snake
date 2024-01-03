package Management;

import Environment.Food.FoodFactory;
import Environment.Food.FoodNames;
import Environment.GameField;
import Environment.IObject;
import Environment.Obstacle.IShape;
import Environment.Obstacle.ObstacleFactory;
import Environment.Obstacle.ObstacleForms.CrossWall;
import Environment.Obstacle.ObstacleForms.Wall;
import Environment.Obstacle.ObstacleNames;
import Environment.Obstacle.Treasure;
import Environment.Position;
import Management.Interface.GameWindow;
import Management.SnakeManagement.Snake;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class ObjectManager {

    private final Position[] spawnPositions = {
            new Position((((GameWindow.getInstance().getWidth() / GameWindow.getInstance().getSizeBlock()) / 4) * GameWindow.getInstance().getSizeBlock()) - (GameWindow.getInstance().getSizeBlock() * 3), (((GameWindow.getInstance().getHeight() / GameWindow.getInstance().getSizeBlock()) / 4) * GameWindow.getInstance().getSizeBlock()) - (GameWindow.getInstance().getSizeBlock() * 2)),
            new Position((((GameWindow.getInstance().getWidth() / GameWindow.getInstance().getSizeBlock() * 3) / 4) * GameWindow.getInstance().getSizeBlock()) + (GameWindow.getInstance().getSizeBlock()), (((GameWindow.getInstance().getHeight() / GameWindow.getInstance().getSizeBlock()) / 4) * GameWindow.getInstance().getSizeBlock()) - (GameWindow.getInstance().getSizeBlock() * 2)),
            new Position((((GameWindow.getInstance().getWidth() / GameWindow.getInstance().getSizeBlock() * 3) / 4) * GameWindow.getInstance().getSizeBlock()) + (GameWindow.getInstance().getSizeBlock()), ((GameWindow.getInstance().getHeight() / GameWindow.getInstance().getSizeBlock() * 3) / 4) * GameWindow.getInstance().getSizeBlock()),
            new Position((((GameWindow.getInstance().getWidth() / GameWindow.getInstance().getSizeBlock()) / 4) * GameWindow.getInstance().getSizeBlock()) - (GameWindow.getInstance().getSizeBlock() * 3), ((GameWindow.getInstance().getHeight() / GameWindow.getInstance().getSizeBlock() * 3) / 4) * GameWindow.getInstance().getSizeBlock())
    };
    private final ObstacleNames[] obstacleNames = ObstacleNames.values();
    public IObject treasure;
    private Boolean treasureExisting = false;
    public IObject currentFood;
    public IShape[] wallStructures = new IShape[5];
    private final FoodNames[] foodNames = FoodNames.values();
    private Boolean foodExisting = false;
    private final Random rnd = new Random();
    private static final Logger objectManagerLogger = LogManager.getLogger(ObjectManager.class);
    private static ObjectManager instance;

    public static ObjectManager getInstance() {
        if (instance == null) {
            instance = new ObjectManager();
            objectManagerLogger.log(Level.DEBUG, "ObjectManager instance created!");
        }
        return instance;
    }

    /**
     * Creates one new randomized food for the snake to eat
     */
    public void createFood() {
        currentFood = FoodFactory.createFood(randomFood(), randomCoordinate());
    }

    /**
     * @return Random value of the FoodNames Enum
     */
    private FoodNames randomFood() {
        return foodNames[rnd.nextInt(foodNames.length - 1)];
    }

    /**
     * A recursive Method to generate randomized Coordinates for a Position object
     * @return a random generated Position object
     */
    private Position randomCoordinate() {
        Position coordinate = new Position(rnd.nextInt(GameWindow.getInstance().getWidth() / GameWindow.getInstance().getSizeBlock()) * GameWindow.getInstance().getSizeBlock(),
                rnd.nextInt(GameWindow.getInstance().getWidth() / GameWindow.getInstance().getSizeBlock()) * GameWindow.getInstance().getSizeBlock());
        boolean foodOnSnake = Snake.getInstance().getPositions().parallelStream()
                .anyMatch(position -> position.getY() == coordinate.getY() && position.getX() == coordinate.getX());

        if (foodOnSnake) {
            objectManagerLogger.log(Level.DEBUG, "Coordinate X: " + coordinate.getX() + ", Y: " + coordinate.getY() + " invalid (on Snake) creating new coordinate");
            return randomCoordinate();
        }
        for (IShape wallStructure : getWallStructures()) {
            for (Wall wall : wallStructure.getWalls()) {
                if (wall.getPosition().getX() == coordinate.getX() &&
                        coordinate.getY() == wall.getPosition().getY()) {
                    objectManagerLogger.log(Level.DEBUG, "Coordinate X: " + coordinate.getX() + ", Y: " + coordinate.getY() + " invalid (on Wall) creating new coordinate");
                    return randomCoordinate();
                }
            }
        }

        objectManagerLogger.log(Level.DEBUG, "New valid coordinate X: " + coordinate.getX() + ", Y: " + coordinate.getY() + " created");
        return coordinate;
    }

    /**
     * @return random value of ObstacleNames Enum
     */
    private ObstacleNames randomObstacle() {
        return obstacleNames[rnd.nextInt(foodNames.length - 1)];
    }

    /**
     * Method creates big wallstructure obstacles as the gamefield is created
     */
    public void createWallStructures() {
        for (int i = 0; i < 4; i++) {
            wallStructures[i] = ObstacleFactory.createWallStructure(randomObstacle(), spawnPositions[i]);
        }
        wallStructures[wallStructures.length - 1] = new CrossWall(new Position(((GameWindow.getInstance().getWidth() / GameWindow.getInstance().getSizeBlock()) / 2) * GameWindow.getInstance().getSizeBlock(), ((GameWindow.getInstance().getHeight() / GameWindow.getInstance().getSizeBlock()) / 2) * GameWindow.getInstance().getSizeBlock()));
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

    public Boolean getTreasureExisting() {
        return treasureExisting;
    }

    public void setTreasureExisting(Boolean obstacleExisting) {
        this.treasureExisting = obstacleExisting;
    }

    public void createTreasure() {
        treasure = new Treasure(randomCoordinate());
    }

    public IObject getCurrenTreasure() {
        return treasure;
    }

    public IShape[] getWallStructures() {
        return wallStructures;
    }

    public void setCurrenTreasure(IObject currenObject) {
        this.treasure = currenObject;
    }
}
