package com.example.ogrebeauty.controller.mainController;

import com.example.ogrebeauty.controller.helpClass.Controller;
import com.example.ogrebeauty.controller.helpClass.WindowManager;
import com.example.ogrebeauty.service.MainService;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Date;

public class MainController extends RedirectController implements Controller {
    private Date selectedDate;
    private MainService mainService;
    @FXML
    private Button firstDate;
    @FXML
    private Button secondDate;
    @FXML
    private Button thirdDate;
    @FXML
    private Button fourthDate;
    @FXML
    private Button fifthDate;
    @FXML
    private Label summByDay;

    public MainController() {
        this.mainService= new MainService();
        this.selectedDate = new Date();
    }
    public void initialize(){
        //this.summByDay.setText("Выручка за день: "+mainService.summPearDay(selectedDate)+"");
        firstDate.setText((selectedDate.getDate()-2)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        secondDate.setText((selectedDate.getDate()-1)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        thirdDate.setText(selectedDate.getDate()+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        fourthDate.setText((selectedDate.getDate()+1)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        fifthDate.setText((selectedDate.getDate()+2)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
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
