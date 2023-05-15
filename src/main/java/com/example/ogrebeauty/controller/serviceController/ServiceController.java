package com.example.ogrebeauty.controller.serviceController;

import com.example.ogrebeauty.Main;
import com.example.ogrebeauty.controller.helpClass.Controller;
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
import java.util.List;

public class ServiceController extends RedirectController implements Controller {
    private ServiceService serviceService;
    @FXML
    public TableView<ServiceDTO> serviceTable;
    @FXML
    public TableColumn<ServiceDTO, String> employeesName;
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
    private List<Service> serviceList;
    private ObservableList<ServiceDTO> observableList;
    public ServiceController(){
        this.serviceService = new ServiceService();
        this.serviceList = serviceService.getListService();
        this.observableList = setTableData(serviceList);
    }
    public ObservableList<ServiceDTO> setTableData(List<Service> serviceList) { //TODO эта функция будет только выводить данные в табилцу. На вход подаётся лист. Надо бы так для всех контроллеров сделать
        ObservableList<ServiceDTO> observableList = FXCollections.observableArrayList();
        /*TODO TEST DATA
        Date date = new Date(2023,02,6);
        date.setHours(23);
        date.setMinutes(31);
        date.setSeconds(0);
        observableList.add(new ServiceDTO(Long.valueOf(1),"Dmitry","Dmitry","test",date.getDate()+"."+date.getMonth()+"."+date.getYear(),date.getHours()+":"+date.getMinutes(),new Date()));

        for(int i=0; i<serviceList.size(); i++){
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
        for(int i=0; i<serviceList.size(); i++){
            observableList.add(new ServiceDTO(
                    serviceList.get(i).getId(),
                    serviceList.get(i).getEmployeesID()+"",
                    serviceList.get(i).getClientID()+"",
                    serviceList.get(i).getServicesID()+"",
                    serviceList.get(i).getData().getDate()+"."+serviceList.get(i).getData().getMonth()+"."+serviceList.get(i).getData().getYear(),
                    serviceList.get(i).getData().getHours()+":"+serviceList.get(i).getData().getMinutes(),
                    serviceList.get(i).getData()
            ));
        }
        return observableList;
    }
    //TODO Можно объеденить поиск и выдачу информации по дефолту
    private void setSearchDataInTable(String data, String name){
        this.observableList = setTableData(serviceService.find(data,name));
    }

    public void initialize(){
        List<String> listWhatIsSearch = new ArrayList<>();
        listWhatIsSearch.add("Сотрудник");
        listWhatIsSearch.add("Клиент");
        listWhatIsSearch.add("Услуга");
        ObservableList<String> listForSearch = FXCollections.observableArrayList(listWhatIsSearch);
        whereSearch.setItems(listForSearch);
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

        serviceTable.setItems(observableList);

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
        EditServiceController controller = (EditServiceController) loader.getController();
        controller.initialize(serviceDTO,stage,windowManager);
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
        DeleteConfirmController controller = (DeleteConfirmController) loader.getController();
        controller.initialize(serviceDTO,stage,windowManager);

        Scene scene = new Scene(paneOne, 300,200);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }
}
