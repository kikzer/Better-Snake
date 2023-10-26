package Managment.Interface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameWindow extends Application {
    private Stage primaryStage = new Stage();
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gameFieldLayout = new GridPane();

        Scene gameField = new Scene(gameFieldLayout, 500, 500);

        //Creating a rectangle for the snakehead
        Rectangle snakeHead = new Rectangle();
        snakeHead.setX(240);
        snakeHead.setY(240);
        snakeHead.setWidth(20);
        snakeHead.setHeight(20);
        snakeHead.setFill(Color.CADETBLUE);

        gameFieldLayout.getChildren().add(snakeHead);

        primaryStage.setTitle("First JavaFX test");

        primaryStage.setScene(gameField);

        primaryStage.show();
    }

    public void changeScene(Scene scene){
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
