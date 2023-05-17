package com.example.ogrebeauty.controller.mainController;

import com.example.ogrebeauty.controller.DTO.ServiceDTO;
import com.example.ogrebeauty.controller.helpClass.Controller;
import com.example.ogrebeauty.controller.helpClass.RedirectController;
import com.example.ogrebeauty.entity.Service;
import com.example.ogrebeauty.service.MainService;
import com.example.ogrebeauty.service.ServiceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class MainController extends RedirectController implements Controller {
    private Date selectedDate;
    private MainService mainService;
    private ServiceService serviceService;
    private ObservableList<ServiceDTO> serviceDTOObservableList;

    @FXML private Label summByDay;
    @FXML private DatePicker calendar;
    @FXML public TableView<ServiceDTO> serviceTable;
    @FXML public TableColumn<ServiceDTO, String> employeesName;
    @FXML private TableColumn<ServiceDTO, String> clientName;
    @FXML private TableColumn<ServiceDTO, String> servicesName;
    @FXML private TableColumn<ServiceDTO, String> time;

    public MainController() {
        this.mainService= new MainService();
        this.serviceService = new ServiceService();
        this.selectedDate = new Date();
        this.serviceDTOObservableList = setObservableList(serviceService.getServiceByDay(selectedDate));
    }
    public void initialize(){

        this.summByDay.setText("Выручка за день: "+mainService.summPearDay(selectedDate)+"₽");
        calendar.setValue(selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        calendar.setOnAction(event -> {
            LocalDate localDate = calendar.getValue();
            Instant instante = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            this.selectedDate=Date.from(instante);
            refreshDataPage();
        });

        serviceDTOObservableList = setObservableList(serviceService.getServiceByDay(selectedDate));
        employeesName.setCellValueFactory(new PropertyValueFactory<>("employeesName"));
        clientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        servicesName.setCellValueFactory(new PropertyValueFactory<>("servicesName"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        serviceTable.setItems(serviceDTOObservableList);
    }
    private ObservableList<ServiceDTO> setObservableList(List<Service> services){
        ObservableList<ServiceDTO> servicesDTOS = FXCollections.observableArrayList();
        for(int i=0; i<services.size(); i++){
            servicesDTOS.add(new ServiceDTO(services.get(i)));
        }
        return servicesDTOS;
    }
    private void refreshDataPage(){
        windowManager.setMainPageDate(selectedDate);
    }
    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
        setTableData(serviceService.getServiceByDay(selectedDate));
    }
    public void setTableData(List<Service> services){
        this.serviceDTOObservableList=setObservableList(services);
    }
}
