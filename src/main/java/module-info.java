module SE2StartupProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.apache.logging.log4j;

    opens Management.Interface;
    exports Management.Interface to javafx.graphics;
    exports Management.Interface.Scenes to javafx.graphics;
    opens Management.Interface.Scenes;
}
