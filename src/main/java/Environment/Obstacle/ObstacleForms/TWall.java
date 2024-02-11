package Environment.Obstacle.ObstacleForms;

import Environment.Obstacle.AShape;
import Environment.Position;
import Management.MetaDataHelper;

public class TWall extends AShape {

    public TWall(Position startingPosition){
        super(startingPosition);
        getWalls().add(new Wall(startingPosition));
        for (int i = 1; i <= 4; i++) {
            getWalls().add(new Wall(new Position(startingPosition.getX(),startingPosition.getY()+(i* MetaDataHelper.SIZEBLOCK))));
        }
        getWalls().add(new Wall(new Position(startingPosition.getX() + MetaDataHelper.SIZEBLOCK,startingPosition.getY() + ((getWalls().size()/2)) * MetaDataHelper.SIZEBLOCK)));
        getWalls().add(new Wall(new Position(startingPosition.getX() + MetaDataHelper.SIZEBLOCK*2,startingPosition.getY() + ((getWalls().size()/2)-1) * MetaDataHelper.SIZEBLOCK)));
    }
}
