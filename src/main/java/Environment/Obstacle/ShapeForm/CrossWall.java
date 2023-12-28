package Environment.Obstacle.ShapeForm;

import Environment.GameField;
import Environment.Obstacle.AShape;
import Environment.Obstacle.ShapeForm.Wall;
import Environment.Position;

public class CrossWall extends AShape {

    public CrossWall(Position startingPosition) {
        super(startingPosition);
        getWalls().add(new Wall(startingPosition));


        getWalls().add(new Wall(new Position(startingPosition.getX(), startingPosition.getY() + (GameField.SIZEBLOCK))));
        getWalls().add(new Wall(new Position(startingPosition.getX(), startingPosition.getY() + (GameField.SIZEBLOCK * 2))));
        getWalls().add(new Wall(new Position(startingPosition.getX(), startingPosition.getY() - (GameField.SIZEBLOCK * 2))));
        getWalls().add(new Wall(new Position(startingPosition.getX(), startingPosition.getY() - (GameField.SIZEBLOCK))));

        getWalls().add(new Wall(new Position(startingPosition.getX() + (GameField.SIZEBLOCK), startingPosition.getY())));
        getWalls().add(new Wall(new Position(startingPosition.getX() + (GameField.SIZEBLOCK * 2), startingPosition.getY())));
        getWalls().add(new Wall(new Position(startingPosition.getX() - (GameField.SIZEBLOCK * 2), startingPosition.getY())));
        getWalls().add(new Wall(new Position(startingPosition.getX() - (GameField.SIZEBLOCK), startingPosition.getY())));
    }
}
