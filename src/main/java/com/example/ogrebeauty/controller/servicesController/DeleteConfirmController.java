package com.example.ogrebeauty.controller.servicesController;

import com.example.ogrebeauty.controller.helpClass.WindowManager;
import com.example.ogrebeauty.entity.Services;
import com.example.ogrebeauty.service.ServicesService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteConfirmController {
    private WindowManager windowManager;
    private ServicesService servicesService;
    private Services services;
    @FXML
    private Label label;
    @FXML
    private Button deleteTrue;
    @FXML
    private Button deleteFalse;
    public void initialize(Services services, ServicesService servicesService, WindowManager windowManager, Stage stage){
        this.services = services;
        this.servicesService = servicesService;
        this.windowManager = windowManager;

        label.setText("Вы уверены что хотите удалить "+services.getServiceType()+"?");

        deleteTrue.setOnAction(event -> {
            stage.close();
            servicesService.deleteById(services.getId());
            try {
                windowManager.redirectToServicesPage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        deleteFalse.setOnAction(event -> {
            stage.close();
            try {
                windowManager.redirectToServicesPage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
