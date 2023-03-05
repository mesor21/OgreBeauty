package com.example.ogrebeauty;

import com.example.ogrebeauty.entity.Client;
import com.example.ogrebeauty.entity.Employees;
import com.example.ogrebeauty.entity.Service;
import com.example.ogrebeauty.repository.ClientRepo;
import com.example.ogrebeauty.repository.EmployeesRepo;
import com.example.ogrebeauty.repository.PostgreSQLJDBC;
import com.example.ogrebeauty.repository.ServiceRepo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Ogre Beaity");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        PostgreSQLJDBC connectionDatabase = new PostgreSQLJDBC();
        connectionDatabase.connectTest();

        /*TODO
        test list

        Save data client without mark
        Save data client with mark
        Save data employees
        Save data service with client
        Save data service with employees
        Save data service without client and employees
        Save data service with client and employees
        Get data service with client
        Get data service with employees
        Get data service without client and employees
        Get data service with client and employees
        Get data client without mark
        Get data client with mark
        Get data employee
        Delete client with mark
        Delete service with client
        Delete service with employees
        Delete service without client and employees

        */

        /*EmployeesRepo employeesRepo=new EmployeesRepo();
        employeesRepo.saveEmployees(new Employees(Long.parseLong("1"), "Dima M","admin", null));
        employeesRepo.findEmployeesById(Long.parseLong("1"));
        employeesRepo.deleteEmployeesById(Long.parseLong("1"), true);

        ClientRepo clientRepo = new ClientRepo();
        clientRepo.saveClient(new Client(Long.parseLong("1"), "Dima", "1234@123","12345", null));
        clientRepo.findClientById(Long.parseLong("1"));
        clientRepo.deleteClientById(Long.parseLong("1"), true);

        ServiceRepo serviceRepo = new ServiceRepo();
        serviceRepo.saveService(new Service(Long.parseLong("1"), "12/12/2023 12:12", "test System", null, null));
        serviceRepo.findServiceById(Long.parseLong("1"));
        serviceRepo.deleteServiceById(Long.parseLong("1"),true);*/

        launch();
    }
}