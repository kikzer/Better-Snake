package Management.Interface;

import Enviroment.GameField;
import Management.GameManager;
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

import java.io.IOException;

public class GameWindow extends Application {

    public final static int WIDTH = 600, HEIGHT = 600;

    private final Canvas canvas = new Canvas(WIDTH, HEIGHT);;
    private GraphicsContext graphicContext;


    private Group root = new Group(canvas);
    private final Scene gameScene = new Scene(root, WIDTH, HEIGHT);
    private final GameField gameField = new GameField(canvas);

    @Override
    public void start(Stage primaryStage) throws IOException {
        createGame(primaryStage);

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
        graphicContext = canvas.getGraphicsContext2D();
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
