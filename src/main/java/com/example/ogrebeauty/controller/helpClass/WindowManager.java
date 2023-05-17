package com.example.ogrebeauty.controller.helpClass;

import com.example.ogrebeauty.Main;
import com.example.ogrebeauty.controller.clientController.ClientController;
import com.example.ogrebeauty.controller.employeesController.EmployeesController;
import com.example.ogrebeauty.controller.mainController.MainController;
import com.example.ogrebeauty.controller.serviceController.ServiceController;
import com.example.ogrebeauty.controller.servicesController.ServicesController;
import com.example.ogrebeauty.entity.Client;
import com.example.ogrebeauty.entity.Employees;
import com.example.ogrebeauty.entity.Service;
import com.example.ogrebeauty.entity.Services;
import com.example.ogrebeauty.service.ServiceService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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
        controller.setWindowManager(this);
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
    public void setMainPageDate(Date date) {
        this.fxmlLoader = new FXMLLoader(Main.class.getResource("mainPage/mainPage.fxml"));
        Pane pane = new Pane();
        try {
            pane = (Pane)fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainController controller = (MainController) fxmlLoader.getController();
        controller.setWindowManager(this);
        controller.setSelectedDate(date);
        controller.initialize();
        scene = new Scene(pane, 1920, 1080);
        stage.setTitle("Ogre Beaity");
        stage.setScene(scene);
        stage.show();
    }
    public void serviceSearch(List<Service> services,String whatSearch,String searchField){
        this.fxmlLoader = new FXMLLoader(Main.class.getResource("servicePage/servicePage.fxml"));
        Pane pane = new Pane();
        try {
            pane = (Pane)fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ServiceController controller = (ServiceController) fxmlLoader.getController();
        controller.setTableData(services);
        controller.setSearchField(whatSearch);
        controller.setSearch(searchField);
        controller.setWindowManager(this);
        controller.initialize();
        scene = new Scene(pane, 1920, 1080);
        stage.setTitle("Ogre Beaity");
        stage.setScene(scene);
        stage.show();
    }
    public void servicesSearch(List<Services> services, String whatSearch, String searchField){
        this.fxmlLoader = new FXMLLoader(Main.class.getResource("services/servicesPage.fxml"));
        Pane pane = new Pane();
        try {
            pane = (Pane)fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ServicesController controller = (ServicesController) fxmlLoader.getController();
        controller.setTableData(services);
        controller.setSearchField(whatSearch);
        controller.setSearch(searchField);
        controller.setWindowManager(this);
        controller.initialize();
        scene = new Scene(pane, 1920, 1080);
        stage.setTitle("Ogre Beaity");
        stage.setScene(scene);
        stage.show();
    }
    public void clientSearch(List<Client> clientList, String whatSearch, String searchField){
        this.fxmlLoader = new FXMLLoader(Main.class.getResource("client/clientPage.fxml"));
        Pane pane = new Pane();
        try {
            pane = (Pane)fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ClientController controller = (ClientController) fxmlLoader.getController();
        controller.setTableData(clientList);
        controller.setSearchField(whatSearch);
        controller.setSearch(searchField);
        controller.setWindowManager(this);
        controller.initialize();
        scene = new Scene(pane, 1920, 1080);
        stage.setTitle("Ogre Beaity");
        stage.setScene(scene);
        stage.show();
    }
    public void employeesSearch(List<Employees> employees,String whatSearch, String searchField){
        this.fxmlLoader = new FXMLLoader(Main.class.getResource("employees/employeesPage.fxml"));
        Pane pane = new Pane();
        try {
            pane = (Pane)fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        EmployeesController controller = (EmployeesController) fxmlLoader.getController();
        controller.setTableData(employees);
        controller.setSearchField(whatSearch);
        controller.setSearch(searchField);
        controller.setWindowManager(this);
        controller.initialize();
        scene = new Scene(pane, 1920, 1080);
        stage.setTitle("Ogre Beaity");
        stage.setScene(scene);
        stage.show();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
