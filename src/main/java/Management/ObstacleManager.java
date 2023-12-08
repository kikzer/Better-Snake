package Management;

import Environment.GameField;
import Environment.IObject;
import Environment.Obstacle.ObstacleFactory;
import Environment.Obstacle.ObstacleNames;
import Environment.Position;
import Management.Interface.GameWindow;

import java.util.Random;

public class ObstacleManager {
    public IObject currenObject;
    private final ObstacleNames[] obstacleNames = ObstacleNames.values();
    private final Random rnd = new Random();

    public ObstacleNames[] getObstacleNames() {
        return obstacleNames;
    }

    public Random getRnd() {
        return rnd;
    }

    public static void setInstance(ObstacleManager instance) {
        ObstacleManager.instance = instance;
    }

    public Boolean getObstacleExisting() {
        return obstacleExisting;
    }

    public void setObstacleExisting(Boolean obstacleExisting) {
        this.obstacleExisting = obstacleExisting;
    }

    private static ObstacleManager instance;
    private Boolean obstacleExisting = false;

    public static ObstacleManager getInstance(){
        if(instance == null){
            instance = new ObstacleManager();
        }
        return instance;
    }

    public void createObstacle(){
        currenObject= ObstacleFactory.createFood(randomObstacle(),new Position(randomCoordinate(),randomCoordinate()));
    }

    private ObstacleNames randomObstacle(){
        return obstacleNames[1];
    }

    private Integer randomCoordinate(){
        return rnd.nextInt(GameWindow.WIDTH/ GameField.SIZEBLOCK)*GameField.SIZEBLOCK;
    }

    public IObject getCurrenObject() {
        return currenObject;
    }

    public void setCurrenObject(IObject currenObject) {
        this.currenObject = currenObject;
    }
}
