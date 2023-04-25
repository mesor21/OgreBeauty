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
    private TextField textField;
    private Stage stage = null;
    private ServiceDTO serviceDTO;
    @FXML
    public void initialize(){
        textField.setText("Вы уверены что хотите удалить запись от "+serviceDTO.getDate()+" "+serviceDTO.getTime()+"?");
        deleteTrue.setOnAction((event -> {
            ServiceService serviceService = new ServiceService();
            System.out.println(DeleteConfirm.class);
        }));
        deleteFalse.setOnAction((event -> {
            ServiceService serviceService = new ServiceService();
            System.out.println(DeleteConfirm.class);
        }));
    }
    public void setServiceDTO(ServiceDTO serviceDTO){
        this.serviceDTO = serviceDTO;
    }
}
