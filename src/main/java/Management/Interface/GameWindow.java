package Management.Interface;

import GameField.GameField;
import Management.GameManager;
import Management.SnakeManagement.Snake;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameWindow extends Application {

    public final static int WIDTH = 600, HEIGHT = 600;

    private GameField gameField;
    private Canvas canvas;
    private GraphicsContext graphicContext;


    private Group root;
    private final Scene gameScene = new Scene(getRoot(), WIDTH, HEIGHT);;

    public GameWindow(){

    }
    @Override
    public void start(Stage primaryStage) throws IOException {;
        createGameOverScene(primaryStage);

    }

    public Group getRoot() {
        return root;
    }

    public Scene getGameScene() {
        return gameScene;
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

    public void createGameOverScene(Stage stage) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("src/main/resources/gameOverScene.fxml")));
        stage.setTitle("Game Over Scene");
        stage.setScene(gameScene);
        stage.setResizable(false);
        stage.show();
    }
    public void createGame(Stage stage) throws IOException {
        canvas = new Canvas(WIDTH, HEIGHT);
        gameField = new GameField(canvas);
        graphicContext = canvas.getGraphicsContext2D();
        root = new Group(canvas);
        stage.setTitle("Better Snake");
        stage.setScene(getGameScene());
        stage.setResizable(false);
        stage.show();
        GameManager manager = new GameManager(this);
        stage.setOnCloseRequest(event -> {
            manager.gameTick.cancel();
        });
    }
}
