module com.example.ogrebeauty {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.ogrebeauty to javafx.fxml;
    exports com.example.ogrebeauty;
}