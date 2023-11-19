package Management.Interface;

import GameField.GameField;
import Management.GameManager;
import Management.SnakeManagement.Snake;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameWindow extends Application {

    public final static int WIDTH = 600, HEIGHT = 600;

    private final GameField gameField;
    private final Stage PRIMARYSTAGE = new Stage();
    private final Canvas canvas;
    private final GraphicsContext graphicContext;

    private final Group root;
    private final Scene gameScene;

    public GameWindow(){
        canvas = new Canvas(WIDTH, HEIGHT);
        gameField = new GameField(canvas);
        graphicContext = canvas.getGraphicsContext2D();
        root = new Group(getCanvas());
        gameScene = new Scene(getRoot(), WIDTH, HEIGHT);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Better Snake");
        primaryStage.setScene(getGameScene());
        primaryStage.setResizable(false);
        primaryStage.show();
        GameManager manager = new GameManager(this);
        primaryStage.setOnCloseRequest(event -> {
            //System.exit(0);
            manager.gameTick.cancel();
        });
    }

    public Group getRoot() {
        return root;
    }

    public Scene getGameScene() {
        return gameScene;
    }

    public void changeScene(Scene scene){
        PRIMARYSTAGE.setScene(scene);
    }

    public void updateBackground(){
        getGameField().createGameField(getGraphicContext());
    }

    public GraphicsContext getGraphicContext() {
        return graphicContext;
    }

    public GameField getGameField() {
        return gameField;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
