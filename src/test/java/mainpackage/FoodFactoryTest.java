package mainpackage;

import Environment.Food.*;
import Environment.Position;
import Management.GameManager;
import Management.Interface.GameWindow;
import Management.ObjectManager;
import javafx.application.Platform;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FoodFactoryTest {
    @Test
    public void testCreateFood() throws IOException {
        assertInstanceOf(Cherry.class, FoodFactory.createFood(FoodNames.CHERRY, new Position(200,200)));
        assertInstanceOf(Apple.class, FoodFactory.createFood(FoodNames.APPLE, new Position(200,200)));
        assertInstanceOf(Banana.class, FoodFactory.createFood(FoodNames.BANANA, new Position(200,200)));
        assertInstanceOf(Kiwi.class, FoodFactory.createFood(FoodNames.KIWI, new Position(200,200)));
        assertInstanceOf(Melon.class, FoodFactory.createFood(FoodNames.MELON, new Position(200,200)));
    }
}
