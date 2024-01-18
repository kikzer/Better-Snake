package mainpackage;

import Management.GameManager;
import Management.Interface.GameWindow;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {

        try{
            Application.launch(GameWindow.class, args);
        }
        catch(Exception e){
            e.printStackTrace();
        }



    }
}
