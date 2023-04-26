package com.example.ogrebeauty.controller.serviceController;

import com.example.ogrebeauty.controller.DTO.ServiceDTO;
import com.example.ogrebeauty.entity.Client;
import com.example.ogrebeauty.entity.Employees;
import com.example.ogrebeauty.entity.Service;
import com.example.ogrebeauty.entity.Services;
import com.example.ogrebeauty.service.ServicesService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class EditService {
    private Service service;
    @FXML
    private ComboBox<Services> servicesComboBox;
    private ObservableList<Services> servicesObservableList;
    @FXML
    private ComboBox<Employees> employeesComboBox;
    private ObservableList<Employees> employees;
    @FXML
    private ComboBox<Client> clientComboBox;
    private ObservableList<Client> clients;
    private Stage stage;
    private AutoCompleteComboBoxListenerForServices<Services> autoCompleteComboBoxListenerForServices;
    private ServicesService servicesService;
    private ServiceDTO serviceDTO;

    public EditService() {
        this.servicesService = new ServicesService();
    }

    public void initialize(){
        servicesComboBox.setEditable(true);
        List<Services> servicesList = new ArrayList<>();
        servicesList.add(new Services(1,"Test",2000));
        servicesList.add(new Services(1,"NeTest",2000));
        //List<Services> servicesList = servicesService.findByServiceType("","serviceType");
        servicesObservableList = FXCollections.observableArrayList(servicesList);
        servicesComboBox.setItems(servicesObservableList);
        autoCompleteComboBoxListenerForServices = new AutoCompleteComboBoxListenerForServices<>(servicesComboBox);
        /*employeesComboBox.setEditable(true);
        clientComboBox.setEditable(true);*/
    }
    public void saveData(Services services){
        //TODO save data
    }

    public void setServiceDTO(ServiceDTO serviceDTO) {
        this.serviceDTO = serviceDTO;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
