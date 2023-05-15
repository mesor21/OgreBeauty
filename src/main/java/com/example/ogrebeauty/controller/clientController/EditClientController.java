package com.example.ogrebeauty.controller.clientController;

import com.example.ogrebeauty.controller.helpClass.WindowManager;
import com.example.ogrebeauty.entity.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

public class EditClientController {
    private WindowManager windowManager;
    private ClientController clientController;
    private Client client;
    @FXML private TextField fullName;
    @FXML private TextField email;
    @FXML private TextField phoneNumber;
    @FXML private TextField mark;
    @FXML private Button confirmData;
    @FXML private Button rejectData;
    //TODO
}
