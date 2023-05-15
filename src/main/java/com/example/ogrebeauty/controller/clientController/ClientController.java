package com.example.ogrebeauty.controller.clientController;

import com.example.ogrebeauty.controller.DTO.ClientDTO;
import com.example.ogrebeauty.controller.helpClass.Controller;
import com.example.ogrebeauty.controller.helpClass.RedirectController;
import com.example.ogrebeauty.entity.Client;
import com.example.ogrebeauty.service.ClientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class ClientController extends RedirectController implements Controller {
    private ClientService clientService;
    private ObservableList<ClientDTO> clientDTOObservableList;
    @FXML public TableView<ClientDTO>  clientTable;
    @FXML private TableColumn<ClientDTO, String> fullName;
    @FXML private TableColumn<ClientDTO, String> email;
    @FXML private TableColumn<ClientDTO, String> phoneNumber;
    @FXML private TableColumn<ClientDTO, String> editButton;
    @FXML private TableColumn<ClientDTO, String> deleteButton;
    private ObservableList<ClientDTO> setObservableList(List<Client> services){
        ObservableList<ClientDTO> servicesDTOS = FXCollections.observableArrayList();
        for(int i=0; i<services.size(); i++){
            servicesDTOS.add(new ClientDTO(services.get(i)));
        }
        return servicesDTOS;
    }
    public void initialize(){

    };
}
