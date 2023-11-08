module SE2StartupProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens Management.Interface;
    exports Management.Interface to javafx.graphics;
}
