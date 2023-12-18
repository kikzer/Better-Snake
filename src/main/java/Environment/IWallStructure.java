package Environment;

import Environment.Obstacle.WallForms;

public interface IWallStructure {
    Position spawnFirstBlock(int quarterNumber);

    void createStructure(WallForms form);

    void showStructure();
}
