package Environment.Obstacle;

import Environment.Obstacle.ObstacleForms.Wall;

import java.util.List;

public interface IShape {

    void showStructure();

    List<Wall> getWalls();
}
