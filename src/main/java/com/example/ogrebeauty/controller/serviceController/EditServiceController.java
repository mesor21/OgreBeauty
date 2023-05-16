package com.example.ogrebeauty.controller.serviceController;

import com.example.ogrebeauty.controller.DTO.ServiceDTO;
import com.example.ogrebeauty.controller.helpClass.WindowManager;
import com.example.ogrebeauty.controller.helpClass.AutoCompleteComboBoxListener;
import com.example.ogrebeauty.controller.helpClass.RedirectController;
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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditServiceController extends RedirectController {
    private ServiceDTO serviceDTO;
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
    private DatePicker datePicker;
    @FXML
    private Button confirmData;
    @FXML
    private Button rejectData;
    private ServiceService serviceService;
    private ServicesService servicesService;
    private EmployeesService employeesService;
    private ClientService clientService;
    private Date date;
    private Services services;
    private Employees employees;
    private Client client;

    public void initialize(ServiceDTO serviceDTO, Stage stage,WindowManager windowManager){

        this.serviceService = new ServiceService();
        this.servicesService = new ServicesService();
        this.employeesService = new EmployeesService();
        this.clientService = new ClientService();

        this.windowManager =windowManager;
        servicesComboBox.setEditable(true);
        employeesComboBox.setEditable(true);
        clientComboBox.setEditable(true);

        boolean itNewService;
        if(serviceDTO.getServicesName()!=null){
            //TODO TEST DATA
            /*servicesComboBox.setValue(new Services(1,"Test",2000));
            employeesComboBox.setValue(new Employees(Long.valueOf(1),"Dima","genius"));
            clientComboBox.setValue(new Client(Long.valueOf(1),"Dima","123@123","1234412",""));*/
            servicesComboBox.setValue(servicesService.findByServiceType(serviceDTO.getServicesName(),"serviceType").get(0));
            employeesComboBox.setValue(employeesService.find(serviceDTO.getEmployeesName(),"fullname").get(0));
            clientComboBox.setValue(clientService.find(serviceDTO.getClientName(),"fullname").get(0));
            itNewService = false;
        }
        else{
            serviceDTO.setDateDate(new Date());
            this.serviceDTO=serviceDTO;
            itNewService = true;
        }

        //TODO TEST DATA
        /*List<Services> servicesList = new ArrayList<>();
        servicesList.add(new Services(1,"Test",2000));
        servicesList.add(new Services(2,"NeTest",2000));*/
        List<Services> servicesList = servicesService.findByServiceType("","serviceType");

        servicesObservableList = FXCollections.observableArrayList(servicesList);
        servicesComboBox.setItems(servicesObservableList);
        servicesComboBox.setVisibleRowCount(5);
        autoCompleteComboBoxListenerForServices = new AutoCompleteComboBoxListener<>(servicesComboBox);

        servicesComboBox.setOnAction(event -> {
            this.services = servicesComboBox.getSelectionModel().getSelectedItem();
        });

        //TODO TEST DATA
        /*List<Employees> employeesList = new ArrayList<>();
        employeesList.add(new Employees(Long.valueOf(1),"Dima","genius"));
        employeesList.add(new Employees(Long.valueOf(2),"Nikita","lox"));*/
        List<Employees> employeesList = employeesService.find("","fullname");
        employeesObservableList = FXCollections.observableArrayList(employeesList);
        employeesComboBox.setItems(employeesObservableList);
        employeesComboBox.setVisibleRowCount(5);
        autoCompleteComboBoxListenerForEmployees = new AutoCompleteComboBoxListener<>(employeesComboBox);

        employeesComboBox.setOnAction(event -> {
            this.employees = employeesComboBox.getSelectionModel().getSelectedItem();
        });

        /*List<Client> clientList = new ArrayList<>();
        //TODO TEST DATA
        clientList.add(new Client(Long.valueOf(1),"Dima","123@123","1234412",""));
        clientList.add(new Client(Long.valueOf(2),"Nikita","123@123","1234412",""));*/
        List<Client> clientList = clientService.find("","fullname");
        clientObservableList = FXCollections.observableArrayList(clientList);
        clientComboBox.setItems(clientObservableList);
        clientComboBox.setVisibleRowCount(5);
        autoCompleteComboBoxListenerForClient = new AutoCompleteComboBoxListener<>(clientComboBox);

        clientComboBox.setOnAction(event -> {
            this.client = clientComboBox.getSelectionModel().getSelectedItem();
        });

        Instant instant = serviceDTO.getDateDate().toInstant();
        datePicker.setValue(instant.atZone(ZoneId.systemDefault()).toLocalDate());
        datePicker.setShowWeekNumbers(true);
        datePicker.setOnAction(event -> {
            LocalDate localDate = datePicker.getValue();
            Instant instante = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            this.serviceDTO.setDateDate(Date.from(instante));
            }
        );

        confirmData.setOnAction(event -> {
            saveData(serviceDTO.getId(),itNewService);
            stage.close();
            try {
                windowManager.redirectToServicePage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        rejectData.setOnAction(event -> {
            stage.close();
        });
    }
    private void saveData(Long id, boolean isNew){
        Service service = new Service(id,serviceDTO.getDateDate(),services,employees,client);
        //System.out.println(service.getServices().toString()+" "+service.getEmploer().toString()+" "+service.getClient().toString()+" "+serviceDTO.getDateDate());
        if(isNew){
            serviceService.saveNewService(service);
        }
        else{
            serviceService.updateService(service);
        }
    }
}
