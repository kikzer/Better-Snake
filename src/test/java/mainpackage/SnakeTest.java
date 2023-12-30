package mainpackage;

import Environment.GameField;
import Environment.Position;
import Management.Interface.GameWindow;
import Management.SnakeManagement.Directions;
import Management.SnakeManagement.Snake;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeTest {

    @Test
    public void testStartPosition() {

        assertEquals(new Position(GameField.SIZEBLOCK, GameField.SIZEBLOCK).getX(), Snake.getInstance().getPositions().get(0).getX());
        assertEquals(new Position(GameField.SIZEBLOCK, GameField.SIZEBLOCK).getY(), Snake.getInstance().getPositions().get(0).getY());
        assertEquals(2, Snake.getInstance().getPositions().size());
    }

    @Test
    public void testMoveDirection(){
        Snake.getInstance().setDirectionEnum(Directions.UP);
        Snake.getInstance().move();

        assertNotEquals(0, Snake.getInstance().getDirection());

        assertEquals(1, Snake.getInstance().getDirection());

        Snake.getInstance().setDirectionEnum(Directions.LEFT);
        Snake.getInstance().move();
        assertEquals(3, Snake.getInstance().getDirection());
        Snake.getInstance().setDirectionEnum(Directions.RIGHT);
        Snake.getInstance().move();
        assertNotEquals(2, Snake.getInstance().getDirection());

        Snake.getInstance().setDirectionEnum(Directions.UP);
        Snake.getInstance().move();
        assertEquals(0, Snake.getInstance().getDirection());
        Snake.getInstance().setDirectionEnum(Directions.DOWN);
        Snake.getInstance().move();
        assertNotEquals(1, Snake.getInstance().getDirection());

        Snake.getInstance().setDirectionEnum(Directions.RIGHT);
        Snake.getInstance().move();
        assertEquals(2, Snake.getInstance().getDirection());

        Snake.getInstance().setDirectionEnum(Directions.DOWN);
        Snake.getInstance().move();
        assertEquals(1, Snake.getInstance().getDirection());

        Snake.getInstance().setDirectionEnum(Directions.LEFT);
        Snake.getInstance().move();
        assertEquals(3, Snake.getInstance().getDirection());

        Snake.getInstance().setDirectionEnum(Directions.UP);
        Snake.getInstance().move();
        assertEquals(0, Snake.getInstance().getDirection());
    }

    @Test
    public void resetTest(){
        Snake.getInstance().setGameOver(true);
        Snake.getInstance().setDirection(2);
        Snake.getInstance().setDirectionEnum(Directions.LEFT);
        Snake.getInstance().getPositions().add(new Position(10 * (GameField.SIZEBLOCK * 5), (5 * GameField.SIZEBLOCK)-GameField.SIZEBLOCK*2));

        Snake.getInstance().reset();
        assertFalse(Snake.getInstance().isGameOver());
        assertEquals(1, Snake.getInstance().getDirection());
        testStartPosition();
        assertEquals(Directions.DOWN, Snake.getInstance().getDirectionEnum());
    }

    @Test
    public void checkBorderTest(){
        Snake.getInstance().getPositions().get(0).setY(GameWindow.WIDTH+GameField.SIZEBLOCK);
        Snake.getInstance().move();
        assertEquals(0,Snake.getInstance().getPositions().get(0).getY());

        Snake.getInstance().getPositions().get(0).setY(-GameField.SIZEBLOCK*2);
        Snake.getInstance().move();
        assertEquals(GameWindow.WIDTH-GameField.SIZEBLOCK,Snake.getInstance().getPositions().get(0).getY());

        Snake.getInstance().getPositions().get(0).setX(-GameField.SIZEBLOCK*2);
        Snake.getInstance().move();
        assertEquals(GameWindow.WIDTH-GameField.SIZEBLOCK,Snake.getInstance().getPositions().get(0).getX());

        Snake.getInstance().getPositions().get(0).setX(GameWindow.WIDTH+GameField.SIZEBLOCK);
        Snake.getInstance().move();
        assertEquals(0,Snake.getInstance().getPositions().get(0).getX());
    }
}
