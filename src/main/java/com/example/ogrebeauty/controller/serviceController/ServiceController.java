package com.example.ogrebeauty.controller.serviceController;

import com.example.ogrebeauty.Main;
import com.example.ogrebeauty.controller.helpClass.Controller;
import com.example.ogrebeauty.controller.DTO.ServiceDTO;
import com.example.ogrebeauty.controller.helpClass.RedirectController;
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
    private List<String> listWhatIsSearch;
    public ServiceController(){
        this.serviceService = new ServiceService();
        this.serviceList = serviceService.getListService();
        setTableData(serviceList);
        this.listWhatIsSearch = new ArrayList<>();
        this.listWhatIsSearch.add("Мастер");
        this.listWhatIsSearch.add("Клиент");
        this.listWhatIsSearch.add("Услуга");
    }
    public void setTableData(List<Service> serviceList) {
        ObservableList<ServiceDTO> observableList = FXCollections.observableArrayList();
        /*if(serviceList==null){
            this.observableList=observableList;
        }*/
        for(int i=0; i<serviceList.size(); i++){
            observableList.add(new ServiceDTO(
                    serviceList.get(i).getId(),
                    serviceList.get(i).getEmploer().getFullName()+"",
                    serviceList.get(i).getClient().getFullName()+"",
                    serviceList.get(i).getServices().getServiceType()+"",
                    serviceList.get(i).getData().getDate()+"."+(serviceList.get(i).getData().getMonth()+1)+"."+(serviceList.get(i).getData().getYear()+1900),
                    serviceList.get(i).getData().getHours()+":"+serviceList.get(i).getData().getMinutes(),
                    serviceList.get(i).getData()
            ));
        }
        this.observableList=observableList;
    }


    public void initialize(){
        ObservableList<String> listForSearch = FXCollections.observableArrayList(listWhatIsSearch);
        whereSearch.setItems(listForSearch);
        if(whereSearch.getValue()==null){
            whereSearch.setValue(listWhatIsSearch.get(0));
        }
        searchConfirm.setOnAction(event -> {
            if(!search.getText().equals("")) {
                if (whereSearch.getValue().equals("Мастер")) {
                    this.serviceList = serviceService.find(search.getText(), "employeeFullname");
                }
                if (whereSearch.getValue().equals("Клиент")) {
                    this.serviceList = serviceService.find(search.getText(), "clientFullname");
                }
                if (whereSearch.getValue().equals("Услуга")) {
                    this.serviceList = serviceService.find(search.getText(), "serviceType");
                }
            }
            else{
                this.serviceList = serviceService.getAll();
            }
            updateData();
                }
        );

        serviceTable.setItems(observableList);

        employeesName.setCellValueFactory(new PropertyValueFactory<>("employeesName"));
        clientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        servicesName.setCellValueFactory(new PropertyValueFactory<>("servicesName"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));

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
            addService();
        });
    }
    private void addService(){
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
    private void updateData(){
        windowManager.serviceSearch(serviceList, (String) whereSearch.getValue());
    }
    public void setSearchField(String searchField){
        for(String value : listWhatIsSearch){
            if(searchField.equals(value)){
                whereSearch.setValue(value);
            }
        }
    }
}
