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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Callable;

public class GameWindow extends Application {

    public final static int WIDTH = 600, HEIGHT = 600;

    private GameField gameField;
    private Canvas canvas;
    private GraphicsContext graphicContext;


    private Group root = new Group();
    private final Scene gameScene = new Scene(getRoot(), WIDTH, HEIGHT);


    @Override
    public void start(Stage primaryStage) throws IOException {
        ;
        createGameOverScene(primaryStage);

    }

    public Group getRoot() {
        return root;
    }

    public Scene getGameScene() {
        return gameScene;
    }

    public void updateBackground() {
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
            manager.getGameTick().cancel();
        });
    }
}
