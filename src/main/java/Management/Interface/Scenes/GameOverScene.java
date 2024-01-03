package Management.Interface.Scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;




public class GameOverScene {
    private static GameOverScene instance;

    public static GameOverScene getInstance() {
        if (instance == null) {
            instance = new GameOverScene();
        }
        return instance;
    }

    public void createGameOverScene(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GameOverScene.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

}
