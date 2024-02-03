package Management;

import Environment.Food.FoodFactory;
import Environment.Food.FoodNames;
import Environment.IObject;
import Environment.Obstacle.IShape;
import Environment.Obstacle.ObstacleFactory;
import Environment.Obstacle.ObstacleForms.CrossWall;
import Environment.Obstacle.ObstacleForms.Wall;
import Environment.Obstacle.ObstacleNames;
import Environment.Obstacle.Treasure;
import Environment.Position;
import Management.SnakeManagement.Snake;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Random;

/**
 * A Manager class to handle all Objects which are shown on screen, such as food walls and treasure chests, but not the player.
 * To create the objects, it uses factories.
 */
public class ObjectManager {

    private final Position[] spawnPositions = {
            new Position((((MetaDataHelper.WIDTH / MetaDataHelper.SIZEBLOCK) / 4) * MetaDataHelper.SIZEBLOCK) - (MetaDataHelper.SIZEBLOCK * 3), (((MetaDataHelper.HEIGHT / MetaDataHelper.SIZEBLOCK) / 4) * MetaDataHelper.SIZEBLOCK) - (MetaDataHelper.SIZEBLOCK * 2)),
            new Position((((MetaDataHelper.WIDTH / MetaDataHelper.SIZEBLOCK * 3) / 4) * MetaDataHelper.SIZEBLOCK) + (MetaDataHelper.SIZEBLOCK), (((MetaDataHelper.HEIGHT / MetaDataHelper.SIZEBLOCK) / 4) * MetaDataHelper.SIZEBLOCK) - (MetaDataHelper.SIZEBLOCK * 2)),
            new Position((((MetaDataHelper.WIDTH / MetaDataHelper.SIZEBLOCK * 3) / 4) * MetaDataHelper.SIZEBLOCK) + (MetaDataHelper.SIZEBLOCK), ((MetaDataHelper.HEIGHT / MetaDataHelper.SIZEBLOCK * 3) / 4) * MetaDataHelper.SIZEBLOCK),
            new Position((((MetaDataHelper.WIDTH / MetaDataHelper.SIZEBLOCK) / 4) * MetaDataHelper.SIZEBLOCK) - (MetaDataHelper.SIZEBLOCK * 3), ((MetaDataHelper.HEIGHT / MetaDataHelper.SIZEBLOCK * 3) / 4) * MetaDataHelper.SIZEBLOCK)
    };
    private final ObstacleNames[] obstacleNames = ObstacleNames.values();
    private IObject treasure;
    private Boolean treasureExisting = false;
    private IObject currentFood;
    private FoodNames currentFoodEnum;
    private final IShape[] wallStructures = new IShape[5];
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

    public Position[] getSpawnPositions() {
        Position[] copySpawnPositions = spawnPositions;
        return copySpawnPositions;
    }

    /**
     * Creates one new randomized food for the snake to eat
     */
    public void createFood() {
        currentFoodEnum = randomFood();
        currentFood = FoodFactory.createFood(currentFoodEnum, randomCoordinate());
    }

    /**
     * @return Random value of the FoodNames Enum
     */
    private FoodNames randomFood() {
        return foodNames[rnd.nextInt(foodNames.length - 1)];
    }

    /**
     * A recursive Method to generate randomized Coordinates for a Position object
     *
     * @return a random generated Position object
     */
    private Position randomCoordinate() {
        Position coordinate = new Position(rnd.nextInt(MetaDataHelper.WIDTH / MetaDataHelper.SIZEBLOCK) * MetaDataHelper.SIZEBLOCK,
                rnd.nextInt(MetaDataHelper.WIDTH / MetaDataHelper.SIZEBLOCK) * MetaDataHelper.SIZEBLOCK);
        //position (.getX() and .getY()), aren't changing while the parallelStream() is running
        boolean foodOnSnake = Snake.getInstance().getPositions().parallelStream()
                .anyMatch(position -> position.getY() == coordinate.getY() && position.getX() == coordinate.getX());

        if (foodOnSnake) {
            objectManagerLogger.log(Level.DEBUG, "Coordinate X: " + coordinate.getX() + ", Y: " + coordinate.getY() + " invalid (on Snake) creating new coordinate");
            return randomCoordinate();
        }
        for (IShape wallStructure : getWallStructures()) {
            if (wallStructure != null) {
                for (Wall wall : wallStructure.getWalls()) {
                    if (wall.getPosition().getX() == coordinate.getX() &&
                            coordinate.getY() == wall.getPosition().getY()) {
                        objectManagerLogger.log(Level.DEBUG, "Coordinate X: " + coordinate.getX() + ", Y: " + coordinate.getY() + " invalid (on Wall) creating new coordinate");
                        return randomCoordinate();
                    }
                }
            }
        }


        objectManagerLogger.log(Level.DEBUG, "New valid coordinate X: " + coordinate.getX() + ", Y: " + coordinate.getY() + " created");
        return coordinate;
    }

    public FoodNames getCurrentFoodEnum() {
        FoodNames copyCurrentFoodEnum = currentFoodEnum;
        return copyCurrentFoodEnum;
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
        wallStructures[wallStructures.length - 1] = new CrossWall(new Position(((MetaDataHelper.WIDTH / MetaDataHelper.SIZEBLOCK) / 2) * MetaDataHelper.SIZEBLOCK, ((MetaDataHelper.HEIGHT / MetaDataHelper.SIZEBLOCK) / 2) * MetaDataHelper.SIZEBLOCK));
    }

    public Boolean getFoodExisting() {
        return foodExisting;
    }

    public void setFoodExisting(Boolean foodExisting) {
        this.foodExisting = foodExisting;
    }

    public IObject getCurrentFood() {
        IObject copyCurrentFood = currentFood;
        return copyCurrentFood;
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
        IObject copyCurrentTreasure = treasure;
        return copyCurrentTreasure;
    }

    public IShape[] getWallStructures() {
        IShape[] copyWallStructures = wallStructures;
        return copyWallStructures;
    }

    public void setCurrenTreasure(IObject currenObject) {
        this.treasure = currenObject;
    }
}
