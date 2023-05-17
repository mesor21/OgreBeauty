package com.example.ogrebeauty.controller.clientController;

import com.example.ogrebeauty.controller.helpClass.WindowManager;
import com.example.ogrebeauty.entity.Client;
import com.example.ogrebeauty.entity.Services;
import com.example.ogrebeauty.service.ClientService;
import com.example.ogrebeauty.service.ServicesService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteConfirmController {
    private WindowManager windowManager;
    private ClientService clientService;
    private Client client;
    @FXML
    private Button deleteTrue;
    @FXML
    private Button deleteFalse;
    public void initialize(Client client, ClientService clientService, WindowManager windowManager, Stage stage){
        this.client = client;
        this.clientService = clientService;
        this.windowManager = windowManager;

        deleteTrue.setOnAction(event -> {
            stage.close();
            clientService.deleteClient(client.getId()+"");
            try {
                windowManager.redirectToClientPage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        deleteFalse.setOnAction(event -> {
            stage.close();
            try {
                windowManager.redirectToClientPage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
