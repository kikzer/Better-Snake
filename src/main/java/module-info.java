module SE2StartupProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens mainpackage;
    exports Managment.Interface to javafx.graphics;
}
