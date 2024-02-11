package Environment.Obstacle.ObstacleForms;

import Environment.Obstacle.AShape;
import Environment.Position;
import Management.MetaDataHelper;

public class CrossWall extends AShape {


    public CrossWall(Position startingPosition) {
        super(startingPosition);
        getWalls().add(new Wall(startingPosition));


        getWalls().add(new Wall(new Position(startingPosition.getX(), startingPosition.getY() + (MetaDataHelper.SIZEBLOCK))));
        getWalls().add(new Wall(new Position(startingPosition.getX(), startingPosition.getY() + (MetaDataHelper.SIZEBLOCK * 2))));
        getWalls().add(new Wall(new Position(startingPosition.getX(), startingPosition.getY() - (MetaDataHelper.SIZEBLOCK * 2))));
        getWalls().add(new Wall(new Position(startingPosition.getX(), startingPosition.getY() - (MetaDataHelper.SIZEBLOCK))));

        getWalls().add(new Wall(new Position(startingPosition.getX() + (MetaDataHelper.SIZEBLOCK), startingPosition.getY())));
        getWalls().add(new Wall(new Position(startingPosition.getX() + (MetaDataHelper.SIZEBLOCK * 2), startingPosition.getY())));
        getWalls().add(new Wall(new Position(startingPosition.getX() - (MetaDataHelper.SIZEBLOCK * 2), startingPosition.getY())));
        getWalls().add(new Wall(new Position(startingPosition.getX() - (MetaDataHelper.SIZEBLOCK), startingPosition.getY())));
    }
}
