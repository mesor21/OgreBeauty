package com.example.ogrebeauty.controller.serviceController;

import com.example.ogrebeauty.controller.DTO.ServiceDTO;
import com.example.ogrebeauty.controller.MainPageController;
import com.example.ogrebeauty.controller.helpClass.AutoCompleteComboBoxListener;
import com.example.ogrebeauty.entity.Client;
import com.example.ogrebeauty.entity.Employees;
import com.example.ogrebeauty.entity.Service;
import com.example.ogrebeauty.entity.Services;
import com.example.ogrebeauty.service.ClientService;
import com.example.ogrebeauty.service.EmployeesService;
import com.example.ogrebeauty.service.ServiceService;
import com.example.ogrebeauty.service.ServicesService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class EditService extends MainPageController{
    private ServiceDTO serviceDTO;
    private Stage stage;

    @FXML
    private ComboBox<Services> servicesComboBox;
    private ObservableList<Services> servicesObservableList;
    private AutoCompleteComboBoxListener<Services> autoCompleteComboBoxListenerForServices;
    @FXML
    private ComboBox<Employees> employeesComboBox;
    private ObservableList<Employees> employeesObservableList;
    private AutoCompleteComboBoxListener<Employees> autoCompleteComboBoxListenerForEmployees;
    @FXML
    private ComboBox<Client> clientComboBox;
    private ObservableList<Client> clientObservableList;
    private AutoCompleteComboBoxListener<Client> autoCompleteComboBoxListenerForClient;
    @FXML
    private Button confirmAllData;
    @FXML
    private Button disableEdit;
    private ServiceService serviceService;
    private ServicesService servicesService;
    private EmployeesService employeesService;
    private ClientService clientService;
    private Date date=new Date();
    private Services services;
    private Employees employees;
    private Client client;

    public EditService() {
        this.serviceService = new ServiceService();
        this.servicesService = new ServicesService();
        this.employeesService = new EmployeesService();
        this.clientService = new ClientService();
    }

    public void initialize(ServiceDTO serviceDTO, Stage stage){
        this.serviceDTO = serviceDTO;
        this.stage = stage;

        servicesComboBox.setEditable(true);
        employeesComboBox.setEditable(true);
        clientComboBox.setEditable(true);

        boolean itNewService;
        if(!serviceDTO.getServicesName().equals("")){
            //TODO TEST DATA
            servicesComboBox.setValue(new Services(1,"Test",2000));
            employeesComboBox.setValue(new Employees(Long.valueOf(1),"Dima","genius"));
            clientComboBox.setValue(new Client(Long.valueOf(1),"Dima","123@123","1234412",""));
            /*servicesComboBox.setValue(servicesService.findByServiceType(serviceDTO.getServicesName(),"serviceType").get(0));
            employeesComboBox.setValue(employeesService.find(serviceDTO.getEmployeesName(),"fullname").get(0));
            clientComboBox.setValue(clientService.find(serviceDTO.getClientName(),"fullname").get(0));*/
            itNewService = false;
        }
        else{
            itNewService = true;
        }

        List<Services> servicesList = new ArrayList<>();
        //TODO TEST DATA
        servicesList.add(new Services(1,"Test",2000));
        servicesList.add(new Services(2,"NeTest",2000));
        //List<Services> servicesList = servicesService.findByServiceType("","serviceType");

        servicesObservableList = FXCollections.observableArrayList(servicesList);
        servicesComboBox.setItems(servicesObservableList);
        servicesComboBox.setVisibleRowCount(5);
        autoCompleteComboBoxListenerForServices = new AutoCompleteComboBoxListener<>(servicesComboBox);

        servicesComboBox.setOnAction(event -> {
            Services services;
            services = servicesComboBox.getSelectionModel().getSelectedItem();
            setServices(services);
        });

        List<Employees> employeesList = new ArrayList<>();
        //TODO TEST DATA
        employeesList.add(new Employees(Long.valueOf(1),"Dima","genius"));
        employeesList.add(new Employees(Long.valueOf(2),"Nikita","lox"));
        //employeesList = employeesService.find("","fullname");
        employeesObservableList = FXCollections.observableArrayList(employeesList);
        employeesComboBox.setItems(employeesObservableList);
        employeesComboBox.setVisibleRowCount(5);
        autoCompleteComboBoxListenerForEmployees = new AutoCompleteComboBoxListener<>(employeesComboBox);

        employeesComboBox.setOnAction(event -> {
            Employees employees;
            employees = employeesComboBox.getSelectionModel().getSelectedItem();
            setEmployees(employees);
        });

        List<Client> clientList = new ArrayList<>();
        //TODO TEST DATA
        clientList.add(new Client(Long.valueOf(1),"Dima","123@123","1234412",""));
        clientList.add(new Client(Long.valueOf(2),"Nikita","123@123","1234412",""));
        //clientList = clientService.find("","fullname");
        clientObservableList = FXCollections.observableArrayList(clientList);
        clientComboBox.setItems(clientObservableList);
        clientComboBox.setVisibleRowCount(5);
        autoCompleteComboBoxListenerForClient = new AutoCompleteComboBoxListener<>(clientComboBox);

        clientComboBox.setOnAction(event -> {
            Client client;
            client = clientComboBox.getSelectionModel().getSelectedItem();
            setClient(client);
        });

        //TODO set Date

        confirmAllData.setOnAction(event -> {
            saveData(serviceDTO.getId(),itNewService);
            stage.close();
            try {
                redirectToServicePage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        disableEdit.setOnAction(event -> {
            stage.close();
        });
    }
    public void saveData(Long id, boolean isNew){
        Service service = new Service(id,date.toString(),services,employees,client);
        System.out.println(service.getServices().toString()+" "+service.getEmploer().toString()+" "+service.getClient().toString());
        if(isNew){
            //serviceService.saveNewService(service); TODO TEST DATA
        }
        else{
            //serviceService.updateService(service); TODO TEST DATA
        }
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
