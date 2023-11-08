package Management.Interface;

import GameField.GameField;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;

public class GameWindow extends Application {
    private final GameField gameField = new GameField();
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        root.getChildren().add(getGameField().getCanvas());
        Scene scene = new Scene(root);
        primaryStage.setTitle("Better Snake");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        getGameField().createGameField(getGameField().getCanvas().getGraphicsContext2D());
    }

    public static void main(String[] args) {
        launch(args);
    }

    public GameField getGameField() {
        return gameField;
    }
}
