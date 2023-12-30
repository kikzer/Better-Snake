package Environment.Obstacle;

import Environment.Obstacle.ObstacleForms.LWall;
import Environment.Obstacle.ObstacleForms.LineWall;
import Environment.Obstacle.ObstacleForms.TWall;
import Environment.Obstacle.ObstacleForms.ZWall;
import Environment.Position;

public class ObstacleFactory {

    /**
     * Factory which can create all types of wall structures in the enum ObstacleNames
     * @param typeWall enum value out of the enum ObstacleNames
     * @param startingPosition x and y position as a Position object
     * @return IShape object in the form of a wall structure
     */
    public static IShape createWallStructure(final ObstacleNames typeWall, final Position startingPosition){
        switch (typeWall){
            case LINEWALL -> {
                return new LineWall(startingPosition);
            }
            case TWALL -> {
                return new TWall(startingPosition);
            }
            case LWALL -> {
                return new LWall(startingPosition);
            }
            case ZWALL -> {
                return new ZWall(startingPosition);
            }
            default -> {
                return null;
            }
        }
    }
}
