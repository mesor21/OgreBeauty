package com.example.ogrebeauty.controller.serviceController;

import com.example.ogrebeauty.controller.DTO.ServiceDTO;
import com.example.ogrebeauty.controller.MainPageController;
import com.example.ogrebeauty.service.ServiceService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;

public class DeleteConfirm extends MainPageController {
    @FXML
    private Button deleteTrue;
    @FXML
    private Button deleteFalse;
    @FXML
    private Label label;
    private ServiceDTO serviceDTO;
    @FXML
    public void initialize(){
        label.setText("Уверены что хотите удалить запись ?");
        deleteTrue.setOnAction((event -> {
            System.out.println(serviceDTO.toString());
            ServiceService serviceService = new ServiceService();
            serviceService.delete(serviceDTO.getId());
        }));
        deleteFalse.setOnAction((event -> {
            openStage.close();
        }));
    }
    public void setServiceDTO(ServiceDTO serviceDTO){
        this.serviceDTO = serviceDTO;
    }
}
