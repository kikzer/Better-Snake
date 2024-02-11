package mainpackage;

import Environment.Obstacle.IShape;
import Environment.Obstacle.ObstacleForms.Wall;
import Environment.Position;
import Management.GameManager;
import Management.Interface.Score;
import Management.MetaDataHelper;
import Management.ObjectManager;
import Management.SnakeManagement.Snake;
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
                        if (wall.getPosition().getY() != newPosition.getY() && wall.getPosition().getX() != newPosition.getX()) {
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
    public void testObjectManagerInitialization() {
        ObjectManager.getInstance().createWallStructures();
        Assertions.assertInstanceOf(IObject.class, ObjectManager.getInstance().getCurrentFood());
        //Checking if coordinates inside the gamefield
        Assertions.assertTrue(ObjectManager.getInstance().getCurrentFood().getPosition().getX() >= 0);
        Assertions.assertTrue(ObjectManager.getInstance().getCurrentFood().getPosition().getX() < 600);
        Assertions.assertTrue(ObjectManager.getInstance().getCurrentFood().getPosition().getY() >= 0);
        Assertions.assertTrue(ObjectManager.getInstance().getCurrentFood().getPosition().getY() < 600);

        Assertions.assertInstanceOf(IObject.class, ObjectManager.getInstance().getCurrenTreasure());
        //Checking if coordinates inside the gamefield
        Assertions.assertTrue(ObjectManager.getInstance().getCurrenTreasure().getPosition().getX() >= 0);
        Assertions.assertTrue(ObjectManager.getInstance().getCurrenTreasure().getPosition().getX() < 600);
        Assertions.assertTrue(ObjectManager.getInstance().getCurrenTreasure().getPosition().getY() >= 0);
        Assertions.assertTrue(ObjectManager.getInstance().getCurrenTreasure().getPosition().getY() < 600);
    }

    @Test
    public void testCheckCollisionIsNotGrowing() throws IOException {
        GameManager.getInstance();
        GameManager.getInstance().checkCollision();
        Snake.getInstance();
        Assertions.assertFalse(Snake.getInstance().isGrowing());
    }

    @Test
    public void testCheckCollisionIsEatingItSelf() throws IOException {
        GameManager.getInstance().gameReset();
        GameManager.getInstance();
        GameManager.getInstance().checkCollision();
        Snake.getInstance().getPositions().get(0).setY(Snake.getInstance().getPositions().get(1).getY());
        Snake.getInstance().getPositions().get(0).setX(Snake.getInstance().getPositions().get(1).getX());
        GameManager.getInstance().checkCollision();
        Assertions.assertTrue(Snake.getInstance().isGameOver());
        GameManager.getInstance().gameReset();
    }

    @Test
    public void testCheckCollisionIsEatingTreasure() throws IOException {
        GameManager.getInstance().gameReset();
        GameManager.getInstance();
        ObjectManager.getInstance().createTreasure();
        Snake.getInstance().getPositions().get(0).setY(ObjectManager.getInstance().getCurrenTreasure().getPosition().getY());
        Snake.getInstance().getPositions().get(0).setX(ObjectManager.getInstance().getCurrenTreasure().getPosition().getX());
        GameManager.getInstance().checkCollision();
        Assertions.assertNull(ObjectManager.getInstance().getCurrenTreasure());
        GameManager.getInstance().gameReset();
    }

    @Test
    public void testCheckCollisionIsEatingFood() throws IOException {
        GameManager.getInstance().gameReset();
        GameManager.getInstance();
        ObjectManager.getInstance().createFood();
        int currentScore = Score.getInstance().getScore();
        Snake.getInstance().getPositions().get(0).setY(ObjectManager.getInstance().getCurrentFood().getPosition().getY());
        Snake.getInstance().getPositions().get(0).setX(ObjectManager.getInstance().getCurrentFood().getPosition().getX());
        GameManager.getInstance().checkCollision();
        Assertions.assertNotEquals(Score.getInstance().getScore(), currentScore);
        GameManager.getInstance().gameReset();
    }
}
