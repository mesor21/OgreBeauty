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
import java.security.spec.ECField;
import java.util.Date;
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
        /*PostgreSQLJDBC connectionDatabase = new PostgreSQLJDBC();
        connectionDatabase.connectTest();*/
        Date date = new Date(2023,02,6);
        date.setHours(23);
        date.setMinutes(31);
        date.setSeconds(0);

        String testdate = date.getYear()+"-"+date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
        System.out.println(testdate);
        System.out.println(date.toString());

        Client clientNullMark = new Client(Long.parseLong("1"),"Ivanova T. M.","123@123.com", "+79054321010",null);
        Client clientMark = new Client(Long.parseLong("2"),"Ivanova T. M.","123@123.com","79054321010", "DCP");
        Employees employees = new Employees(Long.parseLong("1"), "Sidorova A. I.","Старший мастер маникюра", null);
        Service serviceWithClient = new Service(Long.parseLong("1"), date.toString(),"маникюр", clientNullMark, null);
        Service serviceWithEmployees = new Service(Long.parseLong("2"),date.toString(),"маникюр", null, employees);
        Service serviceWithoutClientAndEmployees = new Service(Long.parseLong("3"),date.toString(),"маникюр", null, null);
        Service serviceWithClientAndEmployees = new Service(Long.parseLong("4"),date.toString(),"маникюр", clientNullMark, employees);


        EmployeesRepo employeesRepo=new EmployeesRepo();
        ClientRepo clientRepo = new ClientRepo();
        ServiceRepo serviceRepo = new ServiceRepo();


        /*clientRepo.saveClient(clientNullMark);
        clientRepo.saveClient(clientMark);
        employeesRepo.saveEmployees(employees);
        employeesRepo.saveEmployees(employees);
        serviceRepo.saveService(serviceWithClient);
        serviceRepo.saveService(serviceWithEmployees);
        serviceRepo.saveService(serviceWithoutClientAndEmployees);
        serviceRepo.saveService(serviceWithClientAndEmployees);*/

        System.out.println(serviceRepo.findServiceById(Long.parseLong("1")).toString());
        System.out.println(serviceRepo.findServiceById(Long.parseLong("2")).toString());
        System.out.println(serviceRepo.findServiceById(Long.parseLong("3")).toString());
        System.out.println(serviceRepo.findServiceById(Long.parseLong("4")).toString());
        System.out.println(clientRepo.findClientById(Long.parseLong("1")).toString());
        System.out.println(clientRepo.findClientById(Long.parseLong("2")).toString());
        System.out.println(employeesRepo.findEmployeesById(Long.parseLong("1")).toString());

        clientRepo.deleteClientById(Long.parseLong("2"), true);
        employeesRepo.deleteEmployeesById(Long.parseLong("2"),true);
        serviceRepo.deleteServiceById(Long.parseLong("1"),true);
        serviceRepo.deleteServiceById(Long.parseLong("2"),true);
        serviceRepo.deleteServiceById(Long.parseLong("3"), true);

        serviceRepo.deleteServiceById(Long.parseLong("4"),true);
        employeesRepo.deleteEmployeesById(Long.parseLong("1"), true);
        employeesRepo.deleteEmployeesById(Long.parseLong("2"), true);
        clientRepo.deleteClientById(Long.parseLong("1"),true);
        clientRepo.deleteClientById(Long.parseLong("2"),true);
        launch();
    }
}