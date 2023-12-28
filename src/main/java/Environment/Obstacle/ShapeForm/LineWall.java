package Environment.Obstacle.ShapeForm;

import Environment.GameField;
import Environment.Obstacle.AShape;
import Environment.Position;

public class LineWall extends AShape {

    public LineWall(Position startingPosition){
        super(startingPosition);
        getWalls().add(new Wall(startingPosition));
        for (int i = 1; i <= 4; i++) {
            getWalls().add(new Wall(new Position(startingPosition.getX(),startingPosition.getY()+(i* GameField.SIZEBLOCK))));
        }
    }


}
