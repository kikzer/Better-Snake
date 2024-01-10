package Environment.Obstacle;

import Environment.GameField;
import Environment.AObject;
import Environment.Position;
import Management.Interface.GameWindow;
import Management.MetaDataHelper;
import javafx.scene.image.Image;

/**
 * a treasure box which can be placed in a 2D Environment using the Position class
 */
public class Treasure extends AObject {


    public Treasure(Position position) {
        super(position);
        setAppearance("file:src/main/java/Environment/Images/ObstacleImages/box.png");
    }
}
