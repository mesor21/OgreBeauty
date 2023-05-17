package com.example.ogrebeauty.controller.clientController;

import com.example.ogrebeauty.Main;
import com.example.ogrebeauty.controller.DTO.ClientDTO;
import com.example.ogrebeauty.controller.helpClass.Controller;
import com.example.ogrebeauty.controller.helpClass.RedirectController;
import com.example.ogrebeauty.entity.Client;
import com.example.ogrebeauty.service.ClientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientController extends RedirectController implements Controller {
    private ClientService clientService;
    private ObservableList<ClientDTO> clientDTOObservableList;
    @FXML public TableView<ClientDTO>  clientTable;
    @FXML private TableColumn<ClientDTO, String> fullName;
    @FXML private TableColumn<ClientDTO, String> email;
    @FXML private TableColumn<ClientDTO, String> phoneNumber;
    @FXML private TableColumn<ClientDTO, String> mark;
    @FXML private TableColumn<ClientDTO, String> editButton;
    @FXML private TableColumn<ClientDTO, String> deleteButton;
    @FXML private Button addNewClient;
    @FXML private ComboBox whereSearch;
    @FXML private TextField search;
    @FXML private Button searchConfirm;

    public ClientController() {
        this.clientService = new ClientService();
        List<Client> clientList = clientService.getAll();
        this.clientDTOObservableList=setObservableList(clientList);
    }

    private ObservableList<ClientDTO> setObservableList(List<Client> services){
        ObservableList<ClientDTO> servicesDTOS = FXCollections.observableArrayList();
        for(int i=0; i<services.size(); i++){
            servicesDTOS.add(new ClientDTO(services.get(i)));
        }
        return servicesDTOS;
    }
    public void initialize(){
        List<String> listWhatIsSearch = new ArrayList<>();
        listWhatIsSearch.add("ФИО");
        listWhatIsSearch.add("email");
        listWhatIsSearch.add("Номер телефона");
        listWhatIsSearch.add("Пометка");
        ObservableList<String> listForSearch = FXCollections.observableArrayList(listWhatIsSearch);
        whereSearch.setItems(listForSearch);
        whereSearch.setValue(listForSearch.get(0));
        searchConfirm.setOnAction(event -> {
            List<Client> clientList = new ArrayList<>();
            if(whereSearch.getValue().equals("ФИО")){
                //clientList=clientService.findByServiceType(search.getText(), "serviceType");
            }
            if(whereSearch.getValue().equals("email")){
                //clientList=clientService.findByServiceType(search.getText(), "email");
            }
            if(whereSearch.getValue().equals("Номер телефона")){
                // clientList=clientService.findByServiceType(search.getText(), "phoneNumber");
            }
            if(whereSearch.getValue().equals("Пометка")){
                // clientList=clientService.findByServiceType(search.getText(), "mark");
            }
            this.clientDTOObservableList=setObservableList(clientList);
        });

        fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        mark.setCellValueFactory(new PropertyValueFactory<>("mark"));

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
        clientTable.setItems(clientDTOObservableList);
        addNewClient.setOnAction(event -> {
            newClient();
        });
        //TODO add search
    };
    private void editClient(Client client){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("client/edit.fxml"));
        Stage stage = new Stage();
        Pane pane = null;
        try {
            pane = (Pane)loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        EditClientController controller = (EditClientController) loader.getController();
        controller.initialize(client,clientService,windowManager,stage);
        Scene scene = new Scene(pane, 500,500);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }
    private void deleteConfirm(Client client){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("client/delete.fxml"));
        Stage stage = new Stage();
        Pane pane = null;
        try{
            pane = (Pane)loader.load();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        DeleteConfirmController controller = (DeleteConfirmController) loader.getController();
        controller.initialize(client,clientService,windowManager,stage);
        Scene scene = new Scene(pane, 500,500);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }
    private void newClient(){
        Client client = new Client();
        editClient(client);
    }
}
