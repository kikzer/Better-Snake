package Environment.Obstacle.ObstacleForms;

import Environment.GameField;
import Environment.Obstacle.AShape;
import Environment.Position;
import Management.Interface.GameWindow;

public class LWall extends AShape {


    public LWall(Position startingPosition) {
        super(startingPosition);
        getWalls().add(new Wall(startingPosition));
        for (int i = 1; i <= 3; i++) {
            getWalls().add(new Wall(new Position(startingPosition.getX(), startingPosition.getY() + (i * GameWindow.getInstance().getSizeBlock()))));
        }
        getWalls().add(new Wall(new Position(startingPosition.getX() + GameWindow.getInstance().getSizeBlock(), startingPosition.getY() + ((getWalls().size()-1) * GameWindow.getInstance().getSizeBlock()))));
        getWalls().add(new Wall(new Position(startingPosition.getX() + GameWindow.getInstance().getSizeBlock() * 2, startingPosition.getY() + ((getWalls().size()-2) * GameWindow.getInstance().getSizeBlock()))));
    }
}
