package Environment.Obstacle;

import Environment.Position;
import Management.Interface.GameWindow;

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
