package Environment.Obstacle.ObstacleForms;

import Environment.GameField;
import Environment.Obstacle.AShape;
import Environment.Position;
import Management.Interface.GameWindow;

public class CrossWall extends AShape {


    public CrossWall(Position startingPosition) {
        super(startingPosition);
        getWalls().add(new Wall(startingPosition));


        getWalls().add(new Wall(new Position(startingPosition.getX(), startingPosition.getY() + (GameWindow.getInstance().getSizeBlock()))));
        getWalls().add(new Wall(new Position(startingPosition.getX(), startingPosition.getY() + (GameWindow.getInstance().getSizeBlock() * 2))));
        getWalls().add(new Wall(new Position(startingPosition.getX(), startingPosition.getY() - (GameWindow.getInstance().getSizeBlock() * 2))));
        getWalls().add(new Wall(new Position(startingPosition.getX(), startingPosition.getY() - (GameWindow.getInstance().getSizeBlock()))));

        getWalls().add(new Wall(new Position(startingPosition.getX() + (GameWindow.getInstance().getSizeBlock()), startingPosition.getY())));
        getWalls().add(new Wall(new Position(startingPosition.getX() + (GameWindow.getInstance().getSizeBlock() * 2), startingPosition.getY())));
        getWalls().add(new Wall(new Position(startingPosition.getX() - (GameWindow.getInstance().getSizeBlock() * 2), startingPosition.getY())));
        getWalls().add(new Wall(new Position(startingPosition.getX() - (GameWindow.getInstance().getSizeBlock()), startingPosition.getY())));
    }
}
