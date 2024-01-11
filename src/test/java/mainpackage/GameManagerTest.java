package mainpackage;

import Environment.Obstacle.IShape;
import Environment.Obstacle.ObstacleForms.Wall;
import Environment.Position;
import Management.GameManager;
import Management.MetaDataHelper;
import Management.ObjectManager;
import Management.SnakeManagement.Snake;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import Environment.IObject;

import java.io.IOException;

public class GameManagerTest {

    @Test
    public void winningConditionTest() throws IOException {
        Snake.getInstance().getPositions().clear();
        ObjectManager.getInstance().createWallStructures();
        int maxLength = (MetaDataHelper.SIZEBLOCK * MetaDataHelper.SIZEBLOCK) - MetaDataHelper.SIZEBLOCK;
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < maxLength; j++) {
                Position newPosition = new Position(i * MetaDataHelper.SIZEBLOCK, j * MetaDataHelper.SIZEBLOCK);
                for (IShape structure : ObjectManager.getInstance().getWallStructures()) {
                    for (Wall wall : structure.getWalls()) {
                        if (wall.getPosition().getY() != newPosition.getY() && wall.getPosition().getX() != newPosition.getX()){
                            Snake.getInstance().getPositions().add(newPosition);
                        }
                    }
                }
            }
        }
        GameManager.getInstance();
        Assertions.assertTrue(GameManager.getInstance().isGameWon());
    }

    @BeforeEach
    public void initializeGameManager() throws IOException {
        GameManager.getInstance();
        ObjectManager.getInstance();
        Snake.getInstance();
    }
    @RepeatedTest(50)
    public void testObjectManagerInitialization() throws IOException {
        ObjectManager.getInstance().createWallStructures();
        Assertions.assertInstanceOf(IObject.class, ObjectManager.getInstance().getCurrentFood());
        //Checking if coordinates inside the gamefield
        Assertions.assertTrue(ObjectManager.getInstance().getCurrentFood().getPosition().getX()>=0);
        Assertions.assertTrue(ObjectManager.getInstance().getCurrentFood().getPosition().getX()<600);
        Assertions.assertTrue(ObjectManager.getInstance().getCurrentFood().getPosition().getY()>=0);
        Assertions.assertTrue(ObjectManager.getInstance().getCurrentFood().getPosition().getY()<600);

        Assertions.assertInstanceOf(IObject.class, ObjectManager.getInstance().getCurrenTreasure());
        //Checking if coordinates inside the gamefield
        Assertions.assertTrue(ObjectManager.getInstance().getCurrenTreasure().getPosition().getX()>=0);
        Assertions.assertTrue(ObjectManager.getInstance().getCurrenTreasure().getPosition().getX()<600);
        Assertions.assertTrue(ObjectManager.getInstance().getCurrenTreasure().getPosition().getY()>=0);
        Assertions.assertTrue(ObjectManager.getInstance().getCurrenTreasure().getPosition().getY()<600);
    }
}
