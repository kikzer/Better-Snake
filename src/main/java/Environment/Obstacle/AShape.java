package Environment.Obstacle;

import Environment.Obstacle.ObstacleForms.Wall;
import Environment.Position;

import java.util.ArrayList;
import java.util.List;

public class AShape implements IShape{

    private final List<Wall> walls = new ArrayList<>();
    private final Position startingPosition;
    public AShape(Position startingPosition){
        this.startingPosition = startingPosition;
    }

    @Override
    public List<Wall> getWalls() {
        List<Wall> copyWalls = walls;
        return copyWalls;
    }
}
