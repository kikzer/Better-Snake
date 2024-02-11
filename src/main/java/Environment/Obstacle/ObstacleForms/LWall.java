package Environment.Obstacle.ObstacleForms;

import Environment.Obstacle.AShape;
import Environment.Position;
import Management.MetaDataHelper;

public class LWall extends AShape {


    public LWall(Position startingPosition) {
        super(startingPosition);
        getWalls().add(new Wall(startingPosition));
        for (int i = 1; i <= 3; i++) {
            getWalls().add(new Wall(new Position(startingPosition.getX(), startingPosition.getY() + (i * MetaDataHelper.SIZEBLOCK))));
        }
        getWalls().add(new Wall(new Position(startingPosition.getX() + MetaDataHelper.SIZEBLOCK, startingPosition.getY() + ((getWalls().size()-1) * MetaDataHelper.SIZEBLOCK))));
        getWalls().add(new Wall(new Position(startingPosition.getX() + MetaDataHelper.SIZEBLOCK * 2, startingPosition.getY() + ((getWalls().size()-2) * MetaDataHelper.SIZEBLOCK))));
    }
}
