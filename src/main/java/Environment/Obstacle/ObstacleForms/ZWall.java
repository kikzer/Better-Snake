package Environment.Obstacle.ObstacleForms;

import Environment.GameField;
import Environment.Obstacle.AShape;
import Environment.Position;
import Management.Interface.GameWindow;

public class ZWall extends AShape {

    public ZWall(Position startingPosition) {
        super(new Position(startingPosition.getX()- GameWindow.getInstance().getSizeBlock(),startingPosition.getY()));
        final Position adjustedPosition = new Position(startingPosition.getX()-GameWindow.getInstance().getSizeBlock(),startingPosition.getY()+GameWindow.getInstance().getSizeBlock());
        getWalls().add(new Wall(adjustedPosition));
        for (int i = 1; i <= 5; i++) {
            if (i <= 2) {
                getWalls().add(new Wall(new Position(adjustedPosition.getX() + (i * GameWindow.getInstance().getSizeBlock()), adjustedPosition.getY())));
            } else {
                getWalls().add(new Wall(new Position(adjustedPosition.getX() + ((i-1) * GameWindow.getInstance().getSizeBlock()), adjustedPosition.getY() - GameWindow.getInstance().getSizeBlock())));
            }
        }
    }
}
