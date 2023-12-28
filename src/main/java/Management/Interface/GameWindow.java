package Management.Interface;

import Environment.GameField;
import Management.GameManager;
import Management.ObjectManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * by using the Application class, the GameWindow is the start point of
 * the game Better Snake.
 * from there on, the game gets created and started using the thread in the start() method.
 */
public class GameWindow extends Application{

    public final static int WIDTH = 600, HEIGHT = 600;

    private static GameWindow instance;

    private final Canvas canvas = new Canvas(WIDTH, HEIGHT);;
    private GraphicsContext graphicContext;


    private final Group root = new Group(canvas);
    private final Scene gameScene = new Scene(root, WIDTH, HEIGHT+50);
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
        createGame(primaryStage);
    }

    public Scene getGameScene() {
        return gameScene;
    }

    public void updateBackground() {
        GameField.getInstance(canvas).createGameField(getGraphicContext());
    }

    public GraphicsContext getGraphicContext() {
        return graphicContext;
    }

    /**
     * if the game has been lost, it switches to the game over screen, which lets the game restart
     * @param stage better snake stage (program window)
     */
    public void createGameOverScene(Stage stage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        Button btn = new Button("Reset");

        btn.setMaxWidth(200);
        btn.setMaxHeight(50);

        Label title = new Label("Game Over");
        title.setScaleX(2);
        title.setScaleY(2);
        title.setTranslateY(-70);

        btn.setOnAction((ActionEvent e) -> {
            title.setText("TEST");
            scene.setFill(Color.BLACK);
        });

        root.getChildren().add(title);
        root.getChildren().add(btn);

        stage.setTitle("Game Over");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        gameWindowLogger.log(Level.DEBUG, "Game Over Screen successfully created");
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
        ObjectManager.getInstance().createWallStructures();
        GameManager.getInstance();
        stage.setResizable(false);
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
