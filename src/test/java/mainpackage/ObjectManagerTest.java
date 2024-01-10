package mainpackage;

import Environment.Obstacle.IShape;
import Environment.Obstacle.ObstacleForms.Wall;
import Environment.Position;
import Management.GameManager;
import Management.Interface.GameWindow;
import Management.ObjectManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.Level;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ObjectManagerTest{

    @Test
    public void randomCoordinateTest() {
        String[] args = {};
        GameWindow.launch(GameWindow.class, args);
        Position position = ObjectManager.getInstance().getCurrentFood().getPosition();
        ObjectManager.getInstance().createFood();
        Assertions.assertNotEquals(position, ObjectManager.getInstance().getCurrentFood().getPosition());
    }

    @Test
    public void createWallStructuresTest() throws IOException {
        ObjectManager.getInstance().createWallStructures();
        for (IShape wallStructure : ObjectManager.getInstance().getWallStructures()) {
            Assertions.assertNotNull(wallStructure);
        }
    }
}
