package Environment;

import Environment.Obstacle.ObstacleFactory;
import Management.Interface.GameWindow;
import Management.ObjectManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GameField {
    private final int row = GameWindow.getInstance().getWidth() /GameWindow.getInstance().getSizeBlock();

    private final Canvas canvas;

    private static GameField instance;

    private GameField(final Canvas canvas){
        this.canvas = canvas;
    }
    private static final Logger gameFieldLogger = LogManager.getLogger(GameField.class);

    public static GameField getInstance(final Canvas canvas){
        if (instance == null){
            instance = new GameField(canvas);
            gameFieldLogger.log(Level.DEBUG, "Instance of GameField-Class created");
        }
        return instance;
    }

    /**
     * This Method creates the green/darkgreen pattern in the background of the gamefield.
     * It is called everytime the frame gets updated.
     * @param graphicsContext
     */
    public void createGameField(GraphicsContext graphicsContext){
        for (int i = 0; i < getRow(); i++) {
            for (int j = 0; j < getRow(); j++) {
                if ((i + j) % 2 == 0) {
                    graphicsContext.setFill(Color.GREEN);
                } else {
                    graphicsContext.setFill(Color.DARKGREEN);
                }
                graphicsContext.fillRect(i * GameWindow.getInstance().getSizeBlock(), j * GameWindow.getInstance().getSizeBlock(), GameWindow.getInstance().getSizeBlock(), GameWindow.getInstance().getSizeBlock());
            }
        }
    }

    public int getRow() {
        return row;
    }

    public Canvas getCanvas() {
        return canvas;
    }

}
