package Management.Interface.Scenes;

import Management.GameManager;
import Management.Interface.GameWindow;
import Management.Interface.UiManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.util.Objects;


public class SceneController {

    private Stage stage;
    private Scene scene;

    private static final Logger sceneControllerLogger = LogManager.getLogger(UiManager.class);



    public void switchToMainMenuScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MainMenuScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        sceneControllerLogger.log(Level.DEBUG, "Switched to Main Menu");

    }

    public void switchToHelpScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("HelpScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        sceneControllerLogger.log(Level.DEBUG, "Switched to Help Scene");
    }

    public void switchToGameScene(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GameWindow.getInstance().createGame(stage);
        sceneControllerLogger.log(Level.DEBUG, "Switched to Game Scene");
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
                   sceneControllerLogger.log(Level.DEBUG, "Reset Game");
                   sceneControllerLogger.log(Level.DEBUG, "Switched to Game Scene");
               }
           } catch (Exception e) {
               sceneControllerLogger.log(Level.ERROR, "Couldn't switchback to Game Scene with reset");
               sceneControllerLogger.log(Level.ERROR, e.getMessage());
           }
       });
    }

    public void exitScene(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        sceneControllerLogger.log(Level.DEBUG, "Closed the Program");
        }
}