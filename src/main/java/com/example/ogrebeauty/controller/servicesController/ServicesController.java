package com.example.ogrebeauty.controller.servicesController;

import com.example.ogrebeauty.Main;
import com.example.ogrebeauty.controller.DTO.ServicesDTO;
import com.example.ogrebeauty.controller.helpClass.Controller;
import com.example.ogrebeauty.controller.helpClass.RedirectController;
import com.example.ogrebeauty.entity.Services;
import com.example.ogrebeauty.service.ServicesService;
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

public class ServicesController extends RedirectController implements Controller{
    private ServicesService servicesService;
    private ObservableList<ServicesDTO> servicesObservableList;
    private List<Services> services;
    private List<String> listWhatIsSearch;
    @FXML
    public  TableView<ServicesDTO> servicesTable;
    @FXML
    private TableColumn<ServicesDTO, String> serviceTypeColumn;
    @FXML
    private TableColumn<ServicesDTO, String> priceColumn;
    @FXML
    private TableColumn<ServicesDTO, String> editButton;
    @FXML
    private TableColumn<ServicesDTO, String> deleteButton;
    @FXML
    private Button addNewServices;
    @FXML
    private ComboBox whereSearch;
    @FXML
    private TextField search;
    @FXML
    private Button searchConfirm;

    public ServicesController() {
        this.servicesService = new ServicesService();
        this.services= servicesService.getAll();
        setTableData(services);
        this.listWhatIsSearch = new ArrayList<>();
        this.listWhatIsSearch.add("Тип услуги");
        this.listWhatIsSearch.add("Цена");
    }

    public void setTableData(List<Services> services){
        ObservableList<ServicesDTO> servicesDTOS = FXCollections.observableArrayList();
        for(int i=0; i<services.size(); i++){
            servicesDTOS.add(new ServicesDTO(services.get(i)));
        }
        this.servicesObservableList = servicesDTOS;
    }
    public void initialize(){
        ObservableList<String> listForSearch = FXCollections.observableArrayList(listWhatIsSearch);
        whereSearch.setItems(listForSearch);
        if(whereSearch.getValue()==null){
            whereSearch.setValue(listWhatIsSearch.get(0));
        }
        searchConfirm.setOnAction(event -> {
            if(!search.getText().equals("")) {
                if (whereSearch.getValue().equals("Тип услуги")) {
                    this.services = servicesService.findByServiceType(search.getText(), "serviceType");
                }
                if (whereSearch.getValue().equals("Цена")) {
                    this.services = servicesService.findByServiceType(search.getText(), "price");
                }
            }
            else{
                this.services = servicesService.getAll();
            }
            updateData();
        });

        serviceTypeColumn.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        Callback<TableColumn<ServicesDTO, String>, TableCell<ServicesDTO, String>> editFactory
                = //
                new Callback<TableColumn<ServicesDTO, String>, TableCell<ServicesDTO, String>>() {
                    @Override
                    public TableCell call(final TableColumn<ServicesDTO, String> param) {
                        final TableCell<ServicesDTO, String> cell = new TableCell<ServicesDTO, String>() {

                            final Button btn = new Button("Радактировать");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Services services = getTableView().getItems().get(getIndex()).getServices();
                                        editServices(services);
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
        Callback<TableColumn<ServicesDTO, String>, TableCell<ServicesDTO, String>> deleteFactory
                = //
                new Callback<TableColumn<ServicesDTO, String>, TableCell<ServicesDTO, String>>() {
                    @Override
                    public TableCell call(final TableColumn<ServicesDTO, String> param) {
                        final TableCell<ServicesDTO, String> cell = new TableCell<ServicesDTO, String>() {

                            final Button btn = new Button("Удалить");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Services services = getTableView().getItems().get(getIndex()).getServices();
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

        servicesTable.setItems(servicesObservableList);

        addNewServices.setOnAction(event -> {
            addNewServices();
        });
    };
    private void editServices(Services services){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("services/edit.fxml"));
        Stage stage = new Stage();
        Pane pane = null;
        try {
            pane = (Pane)loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        EditServicesController controller = (EditServicesController)loader.getController();
        controller.initialize(services,servicesService,windowManager,stage);
        Scene scene = new Scene(pane, 500,500);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }
    private void deleteConfirm(Services services){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("services/delete.fxml"));
        Stage stage = new Stage();
        Pane pane = null;
        try{
            pane = (Pane)loader.load();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        DeleteConfirmController controller = (DeleteConfirmController) loader.getController();
        controller.initialize(services,servicesService,windowManager,stage);
        Scene scene = new Scene(pane, 500,500);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }
    private void addNewServices(){
        Services services = new Services();
        editServices(services);
    }
    private void updateData(){
        windowManager.servicesSearch(services, (String) whereSearch.getValue(),search.getText());
    }
    public void setSearchField(String searchField){
        for(String value : listWhatIsSearch){
            if(searchField.equals(value)){
                whereSearch.setValue(value);
            }
        }
    }
    public void setSearch(String search){
        this.search.setText(search);
    }
}
