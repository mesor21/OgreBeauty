package com.example.ogrebeauty.controller.mainController;

import com.example.ogrebeauty.controller.DTO.ServiceDTO;
import com.example.ogrebeauty.controller.DTO.ServicesDTO;
import com.example.ogrebeauty.controller.helpClass.Controller;
import com.example.ogrebeauty.controller.helpClass.RedirectController;
import com.example.ogrebeauty.entity.Service;
import com.example.ogrebeauty.entity.Services;
import com.example.ogrebeauty.service.MainService;
import com.example.ogrebeauty.service.ServiceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class MainController extends RedirectController implements Controller {
    private Date selectedDate;
    private MainService mainService;
    private ServiceService serviceService;
    private ObservableList<ServiceDTO> serviceDTOObservableList;
    @FXML private Button firstDate;
    @FXML private Button secondDate;
    @FXML private Button thirdDate;
    @FXML private Button fourthDate;
    @FXML private Button fifthDate;
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
        this.selectedDate = new Date(123,4,5);
        serviceDTOObservableList = setObservableList(serviceService.getAll());
    }
    public void initialize(){
        //this.summByDay.setText("Выручка за день: "+mainService.summPearDay(selectedDate)+"");
        firstDate.setText((selectedDate.getDate()-2)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        secondDate.setText((selectedDate.getDate()-1)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        thirdDate.setText(selectedDate.getDate()+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        fourthDate.setText((selectedDate.getDate()+1)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        fifthDate.setText((selectedDate.getDate()+2)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        calendar.setValue(selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        calendar.setOnAction(event -> {
            selectedDate = java.sql.Date.valueOf(calendar.getValue());
            refreshPage();
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
    public void onClickFirstDate(){
        this.selectedDate.setDate(selectedDate.getDay()-2);
        refreshPage();
    }
    public void onClickSecondDate(){
        this.selectedDate.setDate(selectedDate.getDay()-1);
        refreshPage();
    }
    public void onClickFourthDate(){
        this.selectedDate.setDate(selectedDate.getDay()+1);
        refreshPage();
    }
    public void onClickFifthDate(){
        this.selectedDate.setDate(selectedDate.getDay()+2);
        refreshPage();
    }
    public void setSummByDay(String summ) {
        Label summByDay = new Label("Выручка за день: "+summ);
        this.summByDay = summByDay;
    }
    private void refreshPage(){
        //Scene scene = new Scene();
        //windowManager.setScene();
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }
}
