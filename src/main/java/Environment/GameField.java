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
    public static final int SIZEBLOCK = 25;
    private final int row = GameWindow.WIDTH /getSizeBlock();

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

    public void createGameField(GraphicsContext graphicsContext){
        for (int i = 0; i < getRow(); i++) {
            for (int j = 0; j < getRow(); j++) {
                if ((i + j) % 2 == 0) {
                    graphicsContext.setFill(Color.GREEN);
                } else {
                    graphicsContext.setFill(Color.DARKGREEN);
                }
                graphicsContext.fillRect(i * getSizeBlock(), j * getSizeBlock(), getSizeBlock(), getSizeBlock());
            }
        }
    }

    public int getSizeBlock() {
        return SIZEBLOCK;
    }

    public int getRow() {
        return row;
    }

    public Canvas getCanvas() {
        return canvas;
    }

}
