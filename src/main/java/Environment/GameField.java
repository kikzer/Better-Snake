package Environment;

import Management.MetaDataHelper;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class represents the gamefield.
 * It is responsible for creating and managing the gamefield.
 * The gamefield is designed as a pattern of alternating green and dark green squares.
 */
public class GameField {
    private final int row = MetaDataHelper.WIDTH /MetaDataHelper.SIZEBLOCK;


    private static GameField instance;

    private GameField(){
    }
    private static final Logger gameFieldLogger = LogManager.getLogger(GameField.class);

    public static GameField getInstance(){
        if (instance == null){
            instance = new GameField();
            gameFieldLogger.log(Level.DEBUG, "Instance of GameField-Class created");
        }
        return instance;
    }

    /**
     * This Method creates the green/darkgreen pattern in the background of the game field.
     * It is called everytime the frame gets updated.
     * @param graphicsContext unlocks the ability to draw on the canvas
     */
    public void createGameField(GraphicsContext graphicsContext){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if ((i + j) % 2 == 0) {
                    graphicsContext.setFill(Color.GREEN);
                } else {
                    graphicsContext.setFill(Color.DARKGREEN);
                }
                graphicsContext.fillRect(i * MetaDataHelper.SIZEBLOCK, j * MetaDataHelper.SIZEBLOCK, MetaDataHelper.SIZEBLOCK, MetaDataHelper.SIZEBLOCK);
            }
        }
    }


}
