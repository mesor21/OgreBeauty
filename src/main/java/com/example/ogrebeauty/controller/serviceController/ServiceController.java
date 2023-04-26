package com.example.ogrebeauty.controller.serviceController;

import com.example.ogrebeauty.Main;
import com.example.ogrebeauty.controller.DTO.ServiceDTO;
import com.example.ogrebeauty.controller.MainPageController;
import com.example.ogrebeauty.entity.Service;
import com.example.ogrebeauty.service.ServiceService;
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
import java.util.List;

public class ServiceController extends MainPageController {

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
    //TODO Можно объеденить поиск и выдачу информации по дефолту
    @FXML
    public void initialize(){
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
                                        openEditStage(serviceDTO);
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
                                        deleteConfirm(serviceDTO);
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
        serviceTable.setItems(setTableData(serviceList));
    }
    public void addNewService(){
        ServiceDTO serviceDTO = new ServiceDTO();
        openEditStage(serviceDTO);

    }
    public void openEditStage(ServiceDTO serviceDTO){

    }
    public void deleteConfirm(ServiceDTO serviceDTO){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("servicePage/delete.fxml"));
        Stage stage = new Stage();
        Pane paneOne = null;
        try {
            paneOne = (Pane)loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DeleteConfirm controller = (DeleteConfirm) loader.getController();
        controller.setStage(stage);
        controller.setServiceDTO(serviceDTO);
        Scene scene = new Scene(paneOne, 300,200);
        stage.setScene(scene);
        stage.initModality(Modality.NONE);
        stage.showAndWait();
    }
}
