module com.example.ogrebeauty {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.ogrebeauty to javafx.fxml;
    opens com.example.ogrebeauty.controller.DTO to javafx.base;
    exports com.example.ogrebeauty;
    exports com.example.ogrebeauty.controller.serviceController;
    opens com.example.ogrebeauty.controller.serviceController to javafx.fxml;
    exports com.example.ogrebeauty.controller.helpClass;
    opens com.example.ogrebeauty.controller.helpClass to javafx.fxml;
    exports com.example.ogrebeauty.controller.mainController;
    opens com.example.ogrebeauty.controller.mainController to javafx.fxml;
    exports com.example.ogrebeauty.controller.clientController;
    opens com.example.ogrebeauty.controller.clientController to javafx.fxml;
    exports com.example.ogrebeauty.controller.employeesController;
    opens com.example.ogrebeauty.controller.employeesController to javafx.fxml;
    exports com.example.ogrebeauty.controller.servicesController;
    opens com.example.ogrebeauty.controller.servicesController to javafx.fxml;
}