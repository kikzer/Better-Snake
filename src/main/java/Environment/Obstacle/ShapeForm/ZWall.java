package Environment.Obstacle.ShapeForm;

import Environment.GameField;
import Environment.Obstacle.AShape;
import Environment.Position;

public class ZWall extends AShape {

    public ZWall(Position startingPosition) {
        super(new Position(startingPosition.getX()-GameField.SIZEBLOCK,startingPosition.getY()));
        final Position adjustedPosition = new Position(startingPosition.getX()-GameField.SIZEBLOCK,startingPosition.getY()+GameField.SIZEBLOCK);
        getWalls().add(new Wall(adjustedPosition));
        for (int i = 1; i <= 5; i++) {
            if (i <= 2) {
                getWalls().add(new Wall(new Position(adjustedPosition.getX() + (i * GameField.SIZEBLOCK), adjustedPosition.getY())));
            } else {
                getWalls().add(new Wall(new Position(adjustedPosition.getX() + ((i-1) * GameField.SIZEBLOCK), adjustedPosition.getY() - GameField.SIZEBLOCK)));
            }
        }
    }
}
