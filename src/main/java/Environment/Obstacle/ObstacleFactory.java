package Environment.Obstacle;

import Environment.Food.*;
import Environment.IObject;
import Environment.IObjectFactory;
import Environment.Position;

public class ObstacleFactory implements IObjectFactory {

    public static IObject createFood(ObstacleNames obstacleNames, Position position){
        switch(obstacleNames){
            case WALL -> {
                return new Wall(position);
            }
            case BOX -> {
                return new Treasure(position);
            }
            default ->{
                return null;
            }
        }
    }
}
