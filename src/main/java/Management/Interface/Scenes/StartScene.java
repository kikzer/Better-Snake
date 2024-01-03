package Management.Interface.Scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StartScene {


    private static StartScene instance;
    public static StartScene getInstance(){
        if(instance == null){
            instance = new StartScene();
        }
        return instance;
    }

    public void createScene(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MainMenuScene.fxml")));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

}