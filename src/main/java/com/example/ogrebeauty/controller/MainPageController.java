package com.example.ogrebeauty.controller;

import com.example.ogrebeauty.Main;
import com.example.ogrebeauty.controller.DTO.ServiceDTO;
import com.example.ogrebeauty.controller.serviceController.ServiceController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {
    protected Stage openStage;
    public MainPageController() {
    }
    public void setStage(Stage stage) {
        this.openStage = stage;
    }
    @FXML
    public void setScene(String nameOfTamplate) throws IOException {
        System.out.println(MainPageController.this);
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(nameOfTamplate));
        Pane pane = (Pane)fxmlLoader.load();
        if(nameOfTamplate.equals("mainPage/mainPage.fxml")) {
            MainPageController controller = (MainPageController) fxmlLoader.getController();
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
}