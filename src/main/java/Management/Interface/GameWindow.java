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
        createStage(primaryStage);
    }

    private void createStage(Stage stage){
        Group root = new Group();
        root.getChildren().add(getGameField().getCanvas());
        Scene scene = new Scene(root);
        stage.setTitle("Better Snake");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        startGame();
    }

    private void startGame(){
        getGameField().createGameField(getGameField().getCanvas().getGraphicsContext2D());

    }

    public static void main(String[] args) {
        launch(args);
    }

    public GameField getGameField() {
        return gameField;
    }
}
