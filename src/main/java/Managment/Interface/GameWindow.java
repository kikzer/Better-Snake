package Managment.Interface;

import Managment.GameManager;
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

    private final int WIDTH, HEIGHT;
    private final Stage PRIMARYSTAGE = new Stage();
    private Canvas canvas;
    private GraphicsContext graphicContext;

    public GameWindow(){
        WIDTH = 600;
        HEIGHT = 800;
        canvas = new Canvas(WIDTH, HEIGHT);
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
        //TODO richtiges gameField implementieren
        graphicContext.setFill(Color.BLACK);
        graphicContext.fillRect(0,0,WIDTH,HEIGHT);
    }

    public GraphicsContext getGraphicContext() {
        return graphicContext;
    }

}
