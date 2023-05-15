package com.example.ogrebeauty.controller.mainController;

import com.example.ogrebeauty.controller.DTO.ServiceDTO;
import com.example.ogrebeauty.controller.helpClass.Controller;
import com.example.ogrebeauty.controller.helpClass.RedirectController;
import com.example.ogrebeauty.service.MainService;
import com.example.ogrebeauty.service.ServiceService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

public class MainController extends RedirectController implements Controller {
    private Date selectedDate;
    private MainService mainService;
    private ServiceService serviceService;

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
        this.selectedDate = new Date();
    }
    public void initialize(){
        //this.summByDay.setText("Выручка за день: "+mainService.summPearDay(selectedDate)+"");
        firstDate.setText((selectedDate.getDate()-2)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        secondDate.setText((selectedDate.getDate()-1)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        thirdDate.setText(selectedDate.getDate()+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        fourthDate.setText((selectedDate.getDate()+1)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        fifthDate.setText((selectedDate.getDate()+2)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        calendar.setValue(selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        
    }

    public void onClickFirstDate(){
        this.selectedDate.setDate(selectedDate.getDay()-2);
    }
    public void onClickSecondDate(){
        this.selectedDate.setDate(selectedDate.getDay()-1);
    }
    public void onClickFourthDate(){
        this.selectedDate.setDate(selectedDate.getDay()+1);
    }
    public void onClickFifthDate(){
        this.selectedDate.setDate(selectedDate.getDay()+2);
    }
    public void setSummByDay(String summ) {
        Label summByDay = new Label("Выручка за день: "+summ);
        this.summByDay = summByDay;
    }
}
