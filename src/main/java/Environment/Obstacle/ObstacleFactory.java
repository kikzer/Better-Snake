package Environment.Obstacle;
import Environment.Obstacle.ObstacleForms.LWall;
import Environment.Obstacle.ObstacleForms.LineWall;
import Environment.Obstacle.ObstacleForms.TWall;
import Environment.Obstacle.ObstacleForms.ZWall;
import Environment.Position;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ObstacleFactory {

    private static final Logger obstacleFactoryLogger = LogManager.getLogger(ObstacleFactory.class);

    /**
     * Factory which can create all types of wall structures in the enum ObstacleNames
     *
     * @param typeWall         enum value out of the enum ObstacleNames
     * @param startingPosition x and y position as a Position object
     * @return IShape object in the form of a wall structure
     */
    public static IShape createWallStructure(final ObstacleNames typeWall, final Position startingPosition) {
        switch (typeWall) {
            case LINEWALL -> {
                obstacleFactoryLogger.log(Level.DEBUG, "Wall of type Line created!");
                return new LineWall(startingPosition);
            }
            case TWALL -> {
                obstacleFactoryLogger.log(Level.DEBUG, "Wall of type T-shaped created!");
                return new TWall(startingPosition);
            }
            case LWALL -> {
                obstacleFactoryLogger.log(Level.DEBUG, "Wall of type L-shaped created!");
                return new LWall(startingPosition);
            }
            case ZWALL -> {
                obstacleFactoryLogger.log(Level.DEBUG, "Wall of type Z-shaped created!");
                return new ZWall(startingPosition);
            }
            default -> {
                obstacleFactoryLogger.log(Level.ERROR, "Wall type not delivered!");
                throw new IllegalArgumentException("Wrong type of Wall structure");
            }
        }
    }
}
