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
    public void createFoodTest() {
        for (int i = 0; i < 50; i++) {
            ObjectManager.getInstance().createFood();
            Position position = ObjectManager.getInstance().getCurrentFood().getPosition();
            ObjectManager.getInstance().createFood();
            Assertions.assertNotEquals(position, ObjectManager.getInstance().getCurrentFood().getPosition());
        }
    }

    @Test
    public void createWallStructuresTest(){
        for (int i = 0; i < 50; i++) {
            ObjectManager.getInstance().createWallStructures();
            for (IShape wallStructure : ObjectManager.getInstance().getWallStructures()) {
                if (wallStructure != null) {
                    for (Wall wall : wallStructure.getWalls()) {
                        Assertions.assertNotNull(wall);
                    }
                }
            }
        }

    }

    @Test
    public void createTreasureTest(){
        for (int i = 0; i < 50; i++) {
            ObjectManager.getInstance().createTreasure();
            Position position = ObjectManager.getInstance().getCurrenTreasure().getPosition();
            ObjectManager.getInstance().createTreasure();
            Assertions.assertNotEquals(position, ObjectManager.getInstance().getCurrenTreasure().getPosition());
        }
    }
}
