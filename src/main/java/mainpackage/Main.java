package mainpackage;

import Managment.GameManager;
import Managment.Interface.GameWindow;
import javafx.application.Application;

public class Main {
    private boolean gameRunning = true;
    public static void main(String[] args) {
        Application.launch(GameWindow.class, args);
    }
}
