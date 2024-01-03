package Environment.Obstacle.ObstacleForms;

import Environment.GameField;
import Environment.AObject;
import Environment.Position;
import Management.Interface.GameWindow;
import javafx.scene.image.Image;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Wall extends AObject {
    private static final Logger wallLogger = LogManager.getLogger(Wall.class);

    public Wall(Position position) {
        super(position);
        wallLogger.log(Level.DEBUG, "Wall at X: "+getPosition().getX()+", Y: "+ getPosition().getY());
        setBlocked(true);
        setAppearance(new Image("file:src/main/java/Environment/Obstacle/ObstacleImages/wallBlock.png", GameWindow.getInstance().getSizeBlock(), GameWindow.getInstance().getSizeBlock(),true,true));
    }
}
