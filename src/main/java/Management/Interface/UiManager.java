package Management.Interface;

import Environment.Images.ImageFactory;
import Environment.Obstacle.ObstacleForms.Wall;
import Environment.Position;
import Management.MetaDataHelper;
import Management.ObjectManager;
import Management.SnakeManagement.Snake;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class manages the ui of the program.
 * It draws everything on screen.
 */
public class UiManager {
    private final Label scoreField = new Label();
    private final Label highScoreField = new Label();
    private static UiManager instance;
    private static final Logger uiManagerLogger = LogManager.getLogger(UiManager.class);

    private UiManager(){
        scoreField.setTextFill(Color.BLACK);
        scoreField.setFont(new Font("Balloon", 24));
        scoreField.setTranslateY(MetaDataHelper.HEIGHT+5);
        scoreField.setTranslateX(MetaDataHelper.SIZEBLOCK);

        highScoreField.setTextFill(Color.BLACK);
        highScoreField.setFont(new Font("Balloon", 24));
        highScoreField.setTranslateY(MetaDataHelper.HEIGHT+5);
        highScoreField.setTranslateX(MetaDataHelper.SIZEBLOCK*17);
    }

    public static UiManager getInstance() {
        if (instance == null) {
            instance = new UiManager();
            uiManagerLogger.log(Level.DEBUG, "UiManager instance created");
        }
        return instance;
    }

    /**
     * Method updates all elements on the gamefield to display the current game frame.
     */
    public void updateGameField() {
        GameWindow.getInstance().updateBackground();
        createVisualObject();
        Snake.getInstance().draw(GameWindow.getInstance().getGraphicContext());
    }

    private void createVisualObject(){
        createImage(ImageFactory.createFoodImage(ObjectManager.getInstance().getCurrentFoodEnum()),ObjectManager.getInstance().getCurrentFood().getPosition());
        if(ObjectManager.getInstance().getCurrenTreasure() != null){
            createImage(ImageFactory.obstacleImages[0],ObjectManager.getInstance().getCurrenTreasure().getPosition());
        }
        for(int i = 0; i < ObjectManager.getInstance().getWallStructures().length;i++){
            for(Wall wall : ObjectManager.getInstance().getWallStructures()[i].getWalls()){
                createImage(ImageFactory.obstacleImages[1], wall.getPosition());
            }
        }
    }

    private void createImage(Image image, Position position){
        GameWindow.getInstance().getGraphicContext().drawImage(image,position.getX(),position.getY());
    }

    public Label getScoreField() {
        return scoreField;
    }

    public Label getHighScoreField() {
        return highScoreField;
    }
}
