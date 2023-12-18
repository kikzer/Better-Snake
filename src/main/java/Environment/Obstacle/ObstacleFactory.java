package Environment.Obstacle;

import Environment.IObject;
import Environment.IWallStructure;
import Environment.Position;

public class ObstacleFactory {

    public static IWallStructure createObstacle(int quarterNumber){
        return new WallStructure(quarterNumber);
    }
}
