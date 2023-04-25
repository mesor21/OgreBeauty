package com.example.ogrebeauty.controller;

import com.example.ogrebeauty.controller.DTO.ServiceDTO;
import com.example.ogrebeauty.entity.Client;
import com.example.ogrebeauty.entity.Employees;
import com.example.ogrebeauty.entity.Service;
import com.example.ogrebeauty.service.ClientService;
import com.example.ogrebeauty.service.EmployeesService;
import com.example.ogrebeauty.service.ServiceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.List;

public class ServiceController extends MainPageController   {

    private ServiceService serviceService;

    public ServiceController(){
        serviceService = new ServiceService();
    }

    @FXML
    private TableView<ServiceDTO> serviceTable;
    @FXML
    private TableColumn<ServiceDTO, String> employeesName;
    @FXML
    private TableColumn<ServiceDTO, String> clientName;
    @FXML
    private TableColumn<ServiceDTO, String> servicesName;
    @FXML
    private TableColumn<ServiceDTO, String> date;
    @FXML
    private TableColumn<ServiceDTO, String> time;
    @FXML
    private TableColumn<ServiceDTO, String> editButton;
    @FXML
    private TableColumn<ServiceDTO, String> deleteButton;
    @FXML
    private TextField employeesNameInput, clientNameInput, servicesNameInput, dateInput, timeInput;
    @FXML
    public ObservableList<ServiceDTO> setTableData(List<Service> serviceList) { //TODO эта функция будет только выводить данные в табилцу. На вход подаётся лист. Надо бы так для всех контроллеров сделать
        // Устанавливаем значения для столбцов

        ObservableList<ServiceDTO> observableList = FXCollections.observableArrayList();
        observableList.add(new ServiceDTO(Long.valueOf(1),"Dmitry","Dmitry","test","12.06.2023","15:00"));

        /*for(int i=0; i<serviceList.size(); i++){
            observableList.add(new ServiceDTO(
                    serviceList.get(i).getId(),
                    serviceList.get(i).getEmploer().getFullName(),
                    serviceList.get(i).getClient().getFullName(),
                    serviceList.get(i).getServices().getServiceType(),
                    serviceList.get(i).getData().toString(), //TODO разделить дату и время
                    serviceList.get(i).getData().toString()
            ));
        }*/
        return observableList;
    }

    @FXML
    public void initialize(){ //TODO Можно объеденить поиск и выдачу информации по дефолту
        employeesName.setCellValueFactory(new PropertyValueFactory<>("employeesName"));
        clientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        servicesName.setCellValueFactory(new PropertyValueFactory<>("servicesName"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        editButton.setCellValueFactory(new PropertyValueFactory<>("editButton"));
        Callback<TableColumn<ServiceDTO, String>, TableCell<ServiceDTO, String>> editFactory
                = //
                new Callback<TableColumn<ServiceDTO, String>, TableCell<ServiceDTO, String>>() {
                    @Override
                    public TableCell call(final TableColumn<ServiceDTO, String> param) {
                        final TableCell<ServiceDTO, String> cell = new TableCell<ServiceDTO, String>() {

                            final Button btn = new Button("Радактировать");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        ServiceDTO serviceDTO = getTableView().getItems().get(getIndex());
                                        //getForEdit(serviceDTO);
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

        deleteButton.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        Callback<TableColumn<ServiceDTO, String>, TableCell<ServiceDTO, String>> deleteFactory
                = //
                new Callback<TableColumn<ServiceDTO, String>, TableCell<ServiceDTO, String>>() {
                    @Override
                    public TableCell call(final TableColumn<ServiceDTO, String> param) {
                        final TableCell<ServiceDTO, String> cell = new TableCell<ServiceDTO, String>() {

                            final Button btn = new Button("Удалить");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        ServiceDTO serviceDTO = getTableView().getItems().get(getIndex());
                                        //add delete confirm
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

        List<Service> serviceList;
        serviceList = serviceService.getListService();
        serviceTable.getColumns().add(editButton);
        serviceTable.getColumns().add(deleteButton);
        serviceTable.setItems(setTableData(serviceList));
    }

    //Edit
    //TODO сделать форму для изменения данных
    /*public void getForEdit(ServiceDTO serviceDTO){
        System.out.println(serviceDTO .toString());
        Service service = serviceService.getService(serviceDTO.getId());
        ClientService clientService = new ClientService();
        EmployeesService employeesService = new EmployeesService();
        String clientField = "";
        String employeeField = "";
        TextField searchField = new TextField();
        searchField.setPromptText("Enter text to search");
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            ListView<String> listView = new ListView<>();
            String searchText = searchField.getText().trim();
            if (!searchText.isEmpty()) {
                List<Client> clientList =  clientService.find(searchField.getText(),"fullname");
                for(int i=0; i<clientList.size(); i++){
                    listView.getItems().add(clientList.get(i).getFullName());
                }
            }
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("Search Results");
            dialog.getDialogPane().setContent(listView);

            ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(okButtonType);

            dialog.showAndWait();
        });
    }*/
    //TODO сохранение изменённых данных

    public void addNewService(){
        Service service = new Service();

    }
}
