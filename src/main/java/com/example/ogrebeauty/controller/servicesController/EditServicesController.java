package com.example.ogrebeauty.controller.servicesController;

import com.example.ogrebeauty.controller.helpClass.WindowManager;
import com.example.ogrebeauty.controller.helpClass.RedirectController;
import com.example.ogrebeauty.entity.Services;
import com.example.ogrebeauty.service.ServicesService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditServicesController extends RedirectController {
    private WindowManager windowManager;
    private ServicesService servicesService;
    private Services services;
    @FXML
    private TextField type;
    @FXML
    private TextField price;
    @FXML
    private Button confirmAllData;
    @FXML
    private Button disableEdit;
    public void initialize(Services services, ServicesService servicesService, WindowManager windowManager, Stage stage){
        this.services = services;
        this.servicesService = servicesService;
        this.windowManager = windowManager;
        boolean isItNew;
        if(services.getServiceType()!=null){
            isItNew = false;
            type.setText(services.getServiceType());
            price.setText(services.getPrice()+"");
        }
        else{
            isItNew = true;
        }
        confirmAllData.setOnAction(event -> {
            services.setServiceType(type.getText());
            services.setPrice(Integer.parseInt(price.getText()));
            saveData(services,isItNew);
            stage.close();
            try {
                windowManager.redirectToServicesPage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        disableEdit.setOnAction(event -> {
            stage.close();
            try {
                windowManager.redirectToServicesPage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void saveData(Services services, boolean isItNew){
        if(isItNew){
            servicesService.saveNewServices(services);
        }
        else{
            servicesService.updateService(services);
        }
    }

}
