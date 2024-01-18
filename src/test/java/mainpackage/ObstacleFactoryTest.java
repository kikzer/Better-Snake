package mainpackage;

import Environment.Obstacle.ObstacleFactory;
import Environment.Obstacle.ObstacleForms.*;
import Environment.Obstacle.ObstacleNames;
import Environment.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ObstacleFactoryTest {
    @Test
    public void createWallStructureTest() {
        assertInstanceOf(LineWall.class, ObstacleFactory.createWallStructure(ObstacleNames.LINEWALL, new Position(200, 200)));
        assertInstanceOf(LWall.class, ObstacleFactory.createWallStructure(ObstacleNames.LWALL, new Position(200, 200)));
        assertInstanceOf(TWall.class, ObstacleFactory.createWallStructure(ObstacleNames.TWALL, new Position(200, 200)));
        assertInstanceOf(ZWall.class, ObstacleFactory.createWallStructure(ObstacleNames.ZWALL, new Position(200, 200)));
    }
}
