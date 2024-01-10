package Environment.Obstacle;

import Environment.Obstacle.ObstacleForms.Wall;

import java.util.List;

public interface IShape {


    /**
     * gets all walls in the wall structure
     * @return list of wall structures
     */
    List<Wall> getWalls();
}
