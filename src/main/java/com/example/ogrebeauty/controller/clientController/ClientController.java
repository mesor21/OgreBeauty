package com.example.ogrebeauty.controller.clientController;

import com.example.ogrebeauty.controller.DTO.ClientDTO;
import com.example.ogrebeauty.controller.helpClass.Controller;
import com.example.ogrebeauty.controller.helpClass.RedirectController;
import com.example.ogrebeauty.entity.Client;
import com.example.ogrebeauty.service.ClientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

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

        Callback<TableColumn<ClientDTO, String>, TableCell<ClientDTO, String>> editFactory
                = //
                new Callback<TableColumn<ClientDTO, String>, TableCell<ClientDTO, String>>() {
                    @Override
                    public TableCell call(final TableColumn<ClientDTO, String> param) {
                        final TableCell<ClientDTO, String> cell = new TableCell<ClientDTO, String>() {

                            final Button btn = new Button("Радактировать");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Client services = getTableView().getItems().get(getIndex()).getClient();
                                        editClient(services);
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        editButton.setCellFactory(editFactory);
        //deleteButton.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        Callback<TableColumn<ClientDTO, String>, TableCell<ClientDTO, String>> deleteFactory
                = //
                new Callback<TableColumn<ClientDTO, String>, TableCell<ClientDTO, String>>() {
                    @Override
                    public TableCell call(final TableColumn<ClientDTO, String> param) {
                        final TableCell<ClientDTO, String> cell = new TableCell<ClientDTO, String>() {

                            final Button btn = new Button("Удалить");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Client services = getTableView().getItems().get(getIndex()).getClient();
                                        deleteConfirm(services);
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        deleteButton.setCellFactory(deleteFactory);
        //TODO add search
        //TODO add data in table, add new button
    };
    private void editClient(Client client){

    }
    private void deleteConfirm(Client client){

    }
}
