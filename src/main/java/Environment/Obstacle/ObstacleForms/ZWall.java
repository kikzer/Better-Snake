package Environment.Obstacle.ObstacleForms;

import Environment.Obstacle.AShape;
import Environment.Position;
import Management.MetaDataHelper;

public class ZWall extends AShape {

    public ZWall(Position startingPosition) {
        super(new Position(startingPosition.getX()- MetaDataHelper.SIZEBLOCK,startingPosition.getY()));
        final Position adjustedPosition = new Position(startingPosition.getX()-MetaDataHelper.SIZEBLOCK,startingPosition.getY()+MetaDataHelper.SIZEBLOCK);
        getWalls().add(new Wall(adjustedPosition));
        for (int i = 1; i <= 5; i++) {
            if (i <= 2) {
                getWalls().add(new Wall(new Position(adjustedPosition.getX() + (i * MetaDataHelper.SIZEBLOCK), adjustedPosition.getY())));
            } else {
                getWalls().add(new Wall(new Position(adjustedPosition.getX() + ((i-1) * MetaDataHelper.SIZEBLOCK), adjustedPosition.getY() - MetaDataHelper.SIZEBLOCK)));
            }
        }
    }
}
