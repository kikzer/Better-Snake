package Management.Interface;

import Environment.GameField;
import Management.GameManager;
import Management.ObjectManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.ResourceBundle;

public class GameWindow extends Application{

    public final static int WIDTH = 600, HEIGHT = 600;

    private static GameWindow instance;

    private final Canvas canvas = new Canvas(WIDTH, HEIGHT);;
    private GraphicsContext graphicContext;


    private final Group root = new Group(canvas);
    private final Scene gameScene = new Scene(root, WIDTH, HEIGHT+50);

    public static GameWindow getInstance(){
        if(instance == null){
            instance = new GameWindow();
        }
        return instance;
    }

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
        GameField.getInstance(canvas).createGameField(getGraphicContext());
    }

    public GraphicsContext getGraphicContext() {
        return graphicContext;
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
        root.getChildren().add(UiManager.getInstance().getScoreField());
        ObjectManager.getInstance().createWallStructures();
        GameManager.getInstance();
        stage.setResizable(false);
        stage.show();

        stage.setOnCloseRequest(event -> {
            try {
                GameManager.getInstance().getGameTick().cancel();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public GameWindow(){
        instance = this;
    }
}
