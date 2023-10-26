package Managment.Interface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameWindow extends Application {
    private final Stage primaryStage = new Stage();
    private int test = 0;
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gameFieldLayout = new GridPane();
        gameFieldLayout.getColumnConstraints().add(new ColumnConstraints(200));
        gameFieldLayout.setGridLinesVisible(true);

        Scene gameField = new Scene(gameFieldLayout, 500, 500);

        //Creating a rectangle for the snakehead
        Rectangle snakeHead = new Rectangle();
        snakeHead.setX(100);
        snakeHead.setWidth(20);
        snakeHead.setHeight(20);
        snakeHead.setFill(Color.CADETBLUE);

        gameFieldLayout.getChildren().add(snakeHead);

        primaryStage.setTitle("First JavaFX test");

        primaryStage.setScene(gameField);

        primaryStage.show();
        test+= 10;
    }

    public void changeScene(Scene scene){
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
