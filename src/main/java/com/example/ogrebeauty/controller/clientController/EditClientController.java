package com.example.ogrebeauty.controller.clientController;

import com.example.ogrebeauty.controller.helpClass.WindowManager;
import com.example.ogrebeauty.entity.Client;
import com.example.ogrebeauty.entity.Services;
import com.example.ogrebeauty.service.ClientService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditClientController {
    private WindowManager windowManager;
    private ClientService clientService;
    private Client client;
    @FXML private TextField fullName;
    @FXML private TextField email;
    @FXML private TextField phoneNumber;
    @FXML private TextField mark;
    @FXML private Button confirmData;
    @FXML private Button rejectData;
    //TODO
    void initialize(Client client, ClientService clientService, WindowManager windowManager, Stage stage){
        this.client = client;
        this.clientService = clientService;
        this.windowManager = windowManager;
        boolean isItNew;
        if(client.getFullName()!=null){
            isItNew = false;
            fullName.setText(client.getFullName());
            email.setText(client.getEmail());
            phoneNumber.setText(client.getPhoneNumber());
            mark.setText(client.getMark());
        }
        else{
            isItNew = true;
        }
        confirmData.setOnAction(event -> {
            stage.close();
            client.setFullName(fullName.getText());
            client.setEmail(email.getText());
            client.setPhoneNumber(phoneNumber.getText());
            client.setMark(mark.getText());
            saveData(client,isItNew);
            try{
                windowManager.redirectToClientPage();
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
        });
        rejectData.setOnAction(event -> {
            stage.close();
            try {
                windowManager.redirectToServicesPage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void saveData(Client client, boolean isItNew){
        if(isItNew){
            clientService.saveNewClient(client);
        }
        else{
            clientService.updateClient(client);
        }
    }
}
