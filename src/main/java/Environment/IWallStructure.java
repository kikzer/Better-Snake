package Environment;

import Environment.Obstacle.Wall;
import Environment.Obstacle.WallForms;

public interface IWallStructure {
    Position spawnFirstBlock(int quarterNumber);

    void createStructure(WallForms form);

    void showStructure();

    Wall[] getWalls();
}
