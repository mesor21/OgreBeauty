package com.example.ogrebeauty.controller.mainController;

import com.example.ogrebeauty.service.ServiceService;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.util.Date;

public class MainController extends RedirectController {
    private Date selectedDate;
    private ServiceService serviceService;
    @FXML
    private Scene scene;
    @FXML
    private Label summByDay;

    public MainController() {
        this.serviceService = new ServiceService();
    }
    public void initialize(){

    }

    public void setSummByDay(String summ) {
        Label summByDay = new Label("Выручка за день: "+summ);
        this.summByDay = summByDay;
    }
}
