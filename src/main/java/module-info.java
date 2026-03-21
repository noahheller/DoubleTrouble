module wpi.noahheller.doubletrouble {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires java.desktop;


    opens wpi.noahheller.doubletrouble to javafx.fxml;
    exports wpi.noahheller.doubletrouble;
}