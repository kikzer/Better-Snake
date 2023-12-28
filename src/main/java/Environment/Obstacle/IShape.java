package Environment.Obstacle;

import Environment.Obstacle.ShapeForm.Wall;

import java.util.List;

public interface IShape {

    void showStructure();

    List<Wall> getWalls();
}
