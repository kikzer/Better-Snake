package Environment.Obstacle;

import Environment.IObject;
import Environment.Position;

public class ObstacleFactory {

    public static IObject createObstacle(ObstacleNames obstacleNames, Position position){
        switch(obstacleNames){
            case WALL -> {
                return new Wall(position);
            }
            default ->{
                return null;
            }
        }
    }
}
