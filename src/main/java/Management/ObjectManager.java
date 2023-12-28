package Management;

import Environment.Food.FoodFactory;
import Environment.Food.FoodNames;
import Environment.GameField;
import Environment.IObject;
import Environment.IWallStructure;
import Environment.Obstacle.ObstacleFactory;
import Environment.Obstacle.Treasure;
import Environment.Obstacle.Wall;
import Environment.Position;
import Management.Interface.GameWindow;
import Management.SnakeManagement.Snake;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class ObjectManager {
    public IObject currentFood;
    public IWallStructure[] wallStructures = new IWallStructure[4];
    private final FoodNames[] foodNames = FoodNames.values();
    private final Random rnd = new Random();

    public IObject treasure;
    private Boolean treasureExisting = false;

    private static final Logger objectManagerLogger = LogManager.getLogger(ObjectManager.class);


    private static ObjectManager instance;
    private Boolean foodExisting = false;

    public static ObjectManager getInstance() {
        if (instance == null) {
            instance = new ObjectManager();
            objectManagerLogger.log(Level.DEBUG, "ObjectManager instance created!");
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
            if(position.getY() == coordinate.getY() && position.getX() == coordinate.getX()){
                objectManagerLogger.log(Level.DEBUG, "Coordinate X: "+coordinate.getX()+", Y: "+coordinate.getY()+" invalid (on Snake) creating new coordinate");
                return randomCoordinate();
            }
        }
        for (IWallStructure wallStructure: getWallStructures()) {
            for (Wall wall : wallStructure.getWalls()) {
                if (wall.getPosition().getX() == coordinate.getX() &&
                        coordinate.getY() == wall.getPosition().getY()){
                    objectManagerLogger.log(Level.DEBUG, "Coordinate X: "+coordinate.getX()+", Y: "+coordinate.getY()+" invalid (on Wall) creating new coordinate");
                    return randomCoordinate();
                }
            }
        }
        objectManagerLogger.log(Level.DEBUG, "New valid coordinate X: "+coordinate.getX()+", Y: "+coordinate.getY()+" created");
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

    public IWallStructure[] getWallStructures() {
        return wallStructures;
    }

    public void setCurrenTreasure(IObject currenObject) {
        this.treasure =currenObject;
    }
}
