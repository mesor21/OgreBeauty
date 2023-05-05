package com.example.ogrebeauty.controller.serviceController;

import com.example.ogrebeauty.Main;
import com.example.ogrebeauty.controller.DTO.ServiceDTO;
import com.example.ogrebeauty.controller.mainController.RedirectController;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceController extends RedirectController {
    //В этом классе нет удаления предыдущего контроллера и мне кажется это может привести в плохим последствиям, если много искать
    private ServiceService serviceService;
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
    private Button addNewService;
    @FXML
    private ComboBox whereSearch;
    @FXML
    private TextField search;
    @FXML
    private Button searchConfirm;
    List<Service> serviceList;
    @FXML
    public ObservableList<ServiceDTO> setTableData(List<Service> serviceList) { //TODO эта функция будет только выводить данные в табилцу. На вход подаётся лист. Надо бы так для всех контроллеров сделать
        // Устанавливаем значения для столбцов  1
        ObservableList<ServiceDTO> observableList = FXCollections.observableArrayList();
        //TODO TEST DATA
        Date date = new Date(2023,02,6);
        date.setHours(23);
        date.setMinutes(31);
        date.setSeconds(0);
        observableList.add(new ServiceDTO(Long.valueOf(1),"Dmitry","Dmitry","test",date.getDate()+"."+date.getMonth()+"."+date.getYear(),date.getHours()+":"+date.getMinutes(),new Date()));

        /*for(int i=0; i<serviceList.size(); i++){
            observableList.add(new ServiceDTO(
                    serviceList.get(i).getId(),
                    serviceList.get(i).getEmploer().getFullName(),
                    serviceList.get(i).getClient().getFullName(),
                    serviceList.get(i).getServices().getServiceType(),
                    serviceList.get(i).getData().getDate()+"."+serviceList.get(i).getData().getMonth()+"."+serviceList.get(i).getData().getYear(),
                    serviceList.get(i).getData().getHours()+":"+serviceList.get(i).getData().getMinutes(),
                    serviceList.get(i).getData()
            ));
        }*/
        return observableList;
    }
    //TODO Можно объеденить поиск и выдачу информации по дефолту
    private void setSearchDataInTable(String data, String name){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("servicePage/servicePage.fxml"));
        Pane paneOne = null;
        try {
            paneOne = (Pane)loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ServiceController serviceController = (ServiceController) loader.getController();
        serviceController.initialize(serviceService.find(data,name));
        serviceController.setOpenStage(openStage);
        Scene scene = new Scene(paneOne, 1920, 1080);
        stage.setTitle("Ogre Beaity");
        stage.setScene(scene);
        openStage.close();
        stage.show();
        openStage = stage;
    }

    public void initialize(List<Service> services){
        serviceList = services;
        serviceTable.setItems(setTableData(serviceList));

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

        addNewService.setOnAction(event -> {
            addNewService();
        });
    }

    public void initialize(){
        List<String> listWhatIsSearch = new ArrayList<>();
        listWhatIsSearch.add("Сотрудник");
        listWhatIsSearch.add("Клиент");
        listWhatIsSearch.add("Услуга");
        ObservableList<String> ListForSearch = FXCollections.observableArrayList(listWhatIsSearch);
        whereSearch.setItems(ListForSearch);
        whereSearch.setValue(listWhatIsSearch.get(0));
        searchConfirm.setOnAction(event -> {
                    if(whereSearch.getValue().equals("Сотрудник")){
                        setSearchDataInTable(search.getText(),"employeeFullname");
                    }
                    if(whereSearch.getValue().equals("Клиент")){
                        setSearchDataInTable(search.getText(),"clientFullname");
                    }
                    if(whereSearch.getValue().equals("Услуга")){
                        setSearchDataInTable(search.getText(),"serviceType");
                    }
                }
        );

        serviceList = serviceService.getListService();
        serviceTable.setItems(setTableData(serviceList));

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

        addNewService.setOnAction(event -> {
            addNewService();
        });
    }

    private void addNewService(){
        ServiceDTO serviceDTO = new ServiceDTO();
        openEditStage(serviceDTO);
    }
    public void openEditStage(ServiceDTO serviceDTO){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("servicePage/edit.fxml"));
        Stage stage = new Stage();
        Pane paneOne = null;
        try {
            paneOne = (Pane)loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        EditService controller = (EditService) loader.getController();
        controller.initialize(serviceDTO,stage);
        controller.setOpenStage(openStage);
        Scene scene = new Scene(paneOne, 500,500);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
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
        controller.initialize(serviceDTO,stage);
        controller.setOpenStage(openStage);
        Scene scene = new Scene(paneOne, 300,200);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }
    public ServiceController(){
        serviceService = new ServiceService();
    }
    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }
}
