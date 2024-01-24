package Management.Interface.Scenes;

import Management.GameManager;
import Management.Interface.GameWindow;
import Management.ObjectManager;
import Management.SnakeManagement.Snake;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.io.IOException;
import java.util.Objects;


public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToMainMenuScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MainMenuScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHelpScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("HelpScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGameScene(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GameWindow.getInstance().createGame(stage);
    }


   public void resetButtonClick (ActionEvent event) throws IOException {

        GameManager.getInstance().gameReset();

       Platform.runLater(() -> {
           try {
               Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               Scene gameScene = GameWindow.getInstance().getGameScene();

               if (gameScene != null) {
                   currentStage.setScene(gameScene);
                   currentStage.show();
               } else {
                   System.err.println("Main game scene reference is null!");
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
       });
    }

    public void exitScene(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        }
}