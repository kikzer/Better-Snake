package mainpackage;

import Environment.Position;
import Management.Interface.GameWindow;
import Management.ObjectManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObjectManagerTest extends Application {

    @Test
    public void randomCoordinateTest() throws Exception {
        String[] args = {};
        GameWindow.launch(GameWindow.class, args);
        Position position = ObjectManager.getInstance().getCurrentFood().getPosition();
        ObjectManager.getInstance().createFood();
        Assertions.assertNotEquals(position, ObjectManager.getInstance().getCurrentFood().getPosition());
    }


    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameWindow.getInstance().start(primaryStage);
    }
}
