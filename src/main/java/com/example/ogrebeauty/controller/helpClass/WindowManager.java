package com.example.ogrebeauty.controller.helpClass;

import com.example.ogrebeauty.Main;
import com.example.ogrebeauty.controller.clientController.ClientController;
import com.example.ogrebeauty.controller.employeesController.EmployeesController;
import com.example.ogrebeauty.controller.mainController.MainController;
import com.example.ogrebeauty.controller.serviceController.ServiceController;
import com.example.ogrebeauty.controller.servicesController.ServicesController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowManager {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private Controller controller;

    public WindowManager() {
        this.stage = new Stage();
    }

    @FXML
    public void setScene(String nameOfTamplate) throws IOException {
        this.fxmlLoader = new FXMLLoader(Main.class.getResource(nameOfTamplate));
        Pane pane = (Pane)fxmlLoader.load();
        if(nameOfTamplate.equals("mainPage/mainPage.fxml")) {
            controller = (MainController) fxmlLoader.getController();
        }
        if(nameOfTamplate.equals("servicePage/servicePage.fxml")) {
            controller = (ServiceController) fxmlLoader.getController();
        }
        if(nameOfTamplate.equals("employees/employeesPage.fxml")) {
            controller = (EmployeesController) fxmlLoader.getController();
        }
        if(nameOfTamplate.equals("client/clientPage.fxml")) {
            controller = (ClientController) fxmlLoader.getController();
        }
        if(nameOfTamplate.equals("services/servicesPage.fxml")) {
            controller = (ServicesController) fxmlLoader.getController();
        }
        controller.initialize();
        scene = new Scene(pane, 1920, 1080);
        stage.setTitle("Ogre Beaity");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void redirectToMainPage() throws IOException {
        setScene("mainPage/mainPage.fxml");
    }
    @FXML
    public void redirectToServicePage() throws IOException {
        setScene("servicePage/servicePage.fxml");
    }
    @FXML
    public void redirectToEmployeesPage() throws IOException {
        setScene("employees/employeesPage.fxml");
    }
    @FXML
    public void redirectToClientPage() throws IOException {
        setScene("client/clientPage.fxml");
    }
    @FXML
    public void redirectToServicesPage() throws IOException {
        setScene("services/servicesPage.fxml");
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
