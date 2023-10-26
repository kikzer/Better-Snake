module SE2StartupProject {
    requires javafx.controls;
    requires javafx.fxml;

    opens mainpackage;
    exports Managment.Interface to javafx.graphics;
}
