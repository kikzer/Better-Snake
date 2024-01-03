package Environment.Obstacle;

import Environment.Obstacle.ObstacleForms.Wall;
import Environment.Position;
import Management.GameManager;
import Management.Interface.GameWindow;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AShape implements IShape{

    private final List<Wall> walls = new ArrayList<>();
    private final Position startingPosition;
    public AShape(Position startingPosition){
        this.startingPosition = startingPosition;
    }
    @Override
    public void showStructure() {
        for (Wall wall : walls) {
            wall.show(GameWindow.getInstance().getGraphicContext());
        }
    }

    @Override
    public List<Wall> getWalls() {
        return walls;
    }
}
