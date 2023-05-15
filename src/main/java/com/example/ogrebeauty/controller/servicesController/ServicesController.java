package com.example.ogrebeauty.controller.servicesController;

import com.example.ogrebeauty.Main;
import com.example.ogrebeauty.controller.helpClass.Controller;
import com.example.ogrebeauty.controller.mainController.RedirectController;
import com.example.ogrebeauty.entity.Services;
import com.example.ogrebeauty.service.ServicesService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private ObservableList<Services> servicesObservableList;
    @FXML
    public  TableView<Services> servicesTable;
    @FXML
    private TableColumn<Services, String> serviceTypeColumn;
    @FXML
    private TableColumn<Services, Integer> priceColumn;
    @FXML
    private TableColumn<Services, String> editButton;
    @FXML
    private TableColumn<Services, String> deleteButton;
    @FXML
    private Button addNewServices;

    public ServicesController() {
        this.servicesService = new ServicesService();
        //List<Services> servicesList= servicesService.findByServiceType("","serviceType");
        List<Services> services = new ArrayList<>();
        services.add(new Services(1,"TestData",1000));
        this.servicesObservableList = FXCollections.observableList(services);//TODO TESTDATA FXCollections.observableList(servicesList);
    }
    public void initialize(){
        servicesTable.setItems(servicesObservableList);

        serviceTypeColumn.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        editButton.setCellValueFactory(new PropertyValueFactory<>("editButton"));
        Callback<TableColumn<Services, String>, TableCell<Services, String>> editFactory
                = //
                new Callback<TableColumn<Services, String>, TableCell<Services, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Services, String> param) {
                        final TableCell<Services, String> cell = new TableCell<Services, String>() {

                            final Button btn = new Button("Радактировать");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Services services = getTableView().getItems().get(getIndex());
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
        deleteButton.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        Callback<TableColumn<Services, String>, TableCell<Services, String>> deleteFactory
                = //
                new Callback<TableColumn<Services, String>, TableCell<Services, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Services, String> param) {
                        final TableCell<Services, String> cell = new TableCell<Services, String>() {

                            final Button btn = new Button("Удалить");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Services services = getTableView().getItems().get(getIndex());
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

        addNewServices.setOnAction(event -> {
            addNewServices();
        });
        servicesTable.setItems(servicesObservableList);
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
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("services/servicesPage.fxml"));
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
}
