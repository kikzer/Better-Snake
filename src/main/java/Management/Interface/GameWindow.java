package Management.Interface;

import Environment.GameField;
import Management.GameManager;
import Management.Interface.Scenes.StartScene;
import Management.MetaDataHelper;
import Management.ObjectManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Objects;

/**
 * by using the Application class, the GameWindow is the start point of
 * the game Better Snake.
 * from there on, the game gets created and started using the thread in the start() method.
 */
public class GameWindow extends Application{


    private static GameWindow instance;

    private final Canvas canvas = new Canvas(MetaDataHelper.WIDTH, MetaDataHelper.HEIGHT);;
    private GraphicsContext graphicContext;


    private final Group root = new Group(canvas);
    private final Scene gameScene = new Scene(root, MetaDataHelper.WIDTH, MetaDataHelper.HEIGHT +50);
    private static final Logger gameWindowLogger = LogManager.getLogger(GameWindow.class);

    public static GameWindow getInstance(){
        if(instance == null){
            instance = new GameWindow();
            gameWindowLogger.log(Level.DEBUG, "GameWindow instance created");
        }
        return instance;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        StartScene.getInstance().createScene(primaryStage);
    }

    public Scene getGameScene() {
        Scene copyGameScene = gameScene;
        return copyGameScene;
    }

    public void updateBackground() {
        GameField.getInstance(canvas).createGameField(getGraphicContext());
    }

    public GraphicsContext getGraphicContext() {
        GraphicsContext copyGraphicsContext = graphicContext;
        return copyGraphicsContext;
    }

    /**
     * creates the game scene, so that Better Snake can be played. in addition, starts the game tick
     * @param stage better snake stage (program window)
     * @throws IOException important for GameManager
     */
    public void createGame(Stage stage) throws IOException {
        graphicContext = canvas.getGraphicsContext2D();
        stage.setTitle("Better Snake");
        stage.setScene(getGameScene());
        root.getChildren().add(UiManager.getInstance().getScoreField());
        root.getChildren().add(UiManager.getInstance().getHighScoreField());
        ObjectManager.getInstance().createWallStructures();
        GameManager.getInstance();
        stage.setResizable(false);

        Image icon = new Image("file:src/main/java/Management/SnakeManagement/SnakeImages/snakeHeadRight.png");
        stage.getIcons().add(icon);


        stage.show();
        gameWindowLogger.log(Level.DEBUG, "Game successfully created");
        stage.setOnCloseRequest(event -> {
            try {
                GameManager.getInstance().getGameTick().cancel();
            } catch (IOException e) {
                gameWindowLogger.log(Level.FATAL, "Failed cancelling of the gameTick, throwing RuntimeException");
                throw new RuntimeException(e);
            }
        });
    }

    public GameWindow(){
        instance = this;
    }
}
