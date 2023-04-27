package com.example.ogrebeauty.controller.mainController;

import com.example.ogrebeauty.Main;
import com.example.ogrebeauty.controller.clientController.ClientController;
import com.example.ogrebeauty.controller.employeesController.EmployeesController;
import com.example.ogrebeauty.controller.servicesController.ServicesController;
import com.example.ogrebeauty.controller.serviceController.ServiceController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class RedirectController {
    protected Stage openStage;
    public RedirectController() {
    }
    public void setStage(Stage stage) {
        this.openStage = stage;
    }
    @FXML
    public void setScene(String nameOfTamplate) throws IOException {
        System.out.println(RedirectController.this);
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(nameOfTamplate));
        Pane pane = (Pane)fxmlLoader.load();
        if(nameOfTamplate.equals("mainPage/mainPage.fxml")) {
            MainController controller = (MainController) fxmlLoader.getController();
            controller.setStage(stage);
        }
        if(nameOfTamplate.equals("servicePage/servicePage.fxml")) {
            ServiceController controller = (ServiceController) fxmlLoader.getController();
            controller.setStage(stage);
        }
        if(nameOfTamplate.equals("employees/employeesPage.fxml")) {
            EmployeesController controller = (EmployeesController) fxmlLoader.getController();
            controller.setStage(stage);
        }
        if(nameOfTamplate.equals("client/clientPage.fxml")) {
            ClientController controller = (ClientController) fxmlLoader.getController();
            controller.setStage(stage);
        }
        if(nameOfTamplate.equals("services/servicesPage.fxml")) {
            ServicesController controller = (ServicesController) fxmlLoader.getController();
            controller.setStage(stage);
        }
        Scene scene = new Scene(pane, 1920, 1080);
        stage.setTitle("Ogre Beaity");
        stage.setScene(scene);
        openStage.close();
        stage.show();
        openStage = stage;
    }
    @FXML
    protected void redirectToServicePage() throws IOException {
        setScene("servicePage/servicePage.fxml");
    }
    @FXML
    protected void redirectToEmployeesPage() throws IOException {
        setScene("employees/employeesPage.fxml");
    }
    @FXML
    protected void redirectToClientPage() throws IOException {
        setScene("client/clientPage.fxml");
    }
    @FXML
    protected void redirectToServicesPage() throws IOException {
        setScene("services/servicesPage.fxml");
    }

    public void setOpenStage(Stage openStage) {
        this.openStage = openStage;
    }
}