package com.example.ogrebeauty.controller.serviceController;

import com.example.ogrebeauty.controller.DTO.ServiceDTO;
import com.example.ogrebeauty.controller.helpClass.WindowManager;
import com.example.ogrebeauty.controller.mainController.RedirectController;
import com.example.ogrebeauty.service.ServiceService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteConfirmController extends RedirectController {
    @FXML
    private Button deleteTrue;
    @FXML
    private Button deleteFalse;
    @FXML
    private Label label;
    private ServiceDTO serviceDTO;
    private Stage stage;
    private ServiceService serviceService;

    public DeleteConfirmController() {
        serviceService = new ServiceService();
    }

    @FXML
    public void initialize(ServiceDTO serviceDTO, Stage stage, WindowManager windowManager){
        this.serviceDTO = serviceDTO;
        this.stage = stage;
        this.windowManager = windowManager;
        label.setText("Уверены что хотите удалить запись" + serviceDTO.getDate()+" "+serviceDTO.getTime()+ " ?");
        deleteTrue.setOnAction(event -> {
            try {
                stage.close();
                delete(serviceDTO.getId());
                windowManager.redirectToServicePage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        deleteFalse.setOnAction(event -> {
            stage.close();
        });
    }
    private void delete(Long id){
        serviceService.delete(id);
    }
}
