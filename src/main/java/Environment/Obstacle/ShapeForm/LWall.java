package Environment.Obstacle.ShapeForm;

import Environment.GameField;
import Environment.Obstacle.AShape;
import Environment.Position;

public class LWall extends AShape {

    public LWall(Position startingPosition) {
        super(startingPosition);
        getWalls().add(new Wall(startingPosition));
        for (int i = 1; i <= 3; i++) {
            getWalls().add(new Wall(new Position(startingPosition.getX(), startingPosition.getY() + (i * GameField.SIZEBLOCK))));
        }
        getWalls().add(new Wall(new Position(startingPosition.getX() + GameField.SIZEBLOCK, startingPosition.getY() + ((getWalls().size()-1) * GameField.SIZEBLOCK))));
        getWalls().add(new Wall(new Position(startingPosition.getX() + GameField.SIZEBLOCK * 2, startingPosition.getY() + ((getWalls().size()-2) * GameField.SIZEBLOCK))));
    }
}
