package Environment.Obstacle;

import Environment.Position;

public class ObstacleFactory {

    public static Environment.Obstacle.IShape createWallStructure(final ObstacleNames typeWall, final Position startingPosition){
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
