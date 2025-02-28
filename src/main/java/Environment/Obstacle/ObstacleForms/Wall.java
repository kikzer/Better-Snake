package Environment.Obstacle.ObstacleForms;

import Environment.AObject;
import Environment.Position;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Wall extends AObject {
    private static final Logger wallLogger = LogManager.getLogger(Wall.class);

    public Wall(Position position) {
        super(position);
        wallLogger.log(Level.DEBUG, "Wall at X: "+getPosition().getX()+", Y: "+ getPosition().getY());
        setBlocked(true);
        setAppearance("file:src/main/java/Environment/Images/ObstacleImages/wallBlock.png");
    }
}
