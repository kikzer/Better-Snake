package Management.Interface;

import GameField.GameField;
import Management.GameManager;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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

    public GameWindow(){
        canvas = new Canvas(WIDTH, HEIGHT);
        gameField = new GameField(canvas);
        graphicContext = canvas.getGraphicsContext2D();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group(canvas);
        Scene gameField = new Scene(group, WIDTH, HEIGHT);
        primaryStage.setTitle("First JavaFX test");
        primaryStage.setScene(gameField);
        primaryStage.show();
        GameManager manager = new GameManager(this);
        primaryStage.setOnCloseRequest(event -> {
            //System.exit(0);
            manager.gameTick.cancel();
        });
    }

    public void changeScene(Scene scene){
        PRIMARYSTAGE.setScene(scene);
    }

    public void updateBackground(){
        gameField.createGameField(getGraphicContext());
    }
    public void drawFood(){

    }

    public GraphicsContext getGraphicContext() {
        return graphicContext;
    }

}
