package com.example.ogrebeauty.controller.employeesController;

import com.example.ogrebeauty.Main;
import com.example.ogrebeauty.controller.DTO.EmployeesDTO;
import com.example.ogrebeauty.controller.employeesController.EditEmployeesController;
import com.example.ogrebeauty.controller.employeesController.DeleteConfirmController;
import com.example.ogrebeauty.controller.helpClass.Controller;
import com.example.ogrebeauty.controller.helpClass.RedirectController;
import com.example.ogrebeauty.entity.Employees;
import com.example.ogrebeauty.service.EmployeesService;
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

public class EmployeesController extends RedirectController implements Controller {
    private EmployeesService employeesService;
    private ObservableList<EmployeesDTO> employeesDTOObservableList;
    @FXML public TableView<EmployeesDTO> employeesTable;
    @FXML private TableColumn<EmployeesDTO, String> fullName;
    @FXML private TableColumn<EmployeesDTO, String> jobTitle;
    @FXML private TableColumn<EmployeesDTO, String> editButton;
    @FXML private TableColumn<EmployeesDTO, String> deleteButton;
    @FXML private Button addNewEmployees;

    public  EmployeesController(){
        employeesService = new EmployeesService();
        List<Employees> employeesList = employeesService.find("","fullname");
    }

    private ObservableList<EmployeesDTO> setObservableList(List<Employees> services){
        ObservableList<EmployeesDTO> servicesDTOS = FXCollections.observableArrayList();
        for(int i=0; i<services.size(); i++){
            servicesDTOS.add(new EmployeesDTO(services.get(i)));
        }
        return servicesDTOS;
    }
    public void initialize(){
        fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        jobTitle.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        Callback<TableColumn<EmployeesDTO, String>, TableCell<EmployeesDTO, String>> editFactory
                = //
                new Callback<TableColumn<EmployeesDTO, String>, TableCell<EmployeesDTO, String>>() {
                    @Override
                    public TableCell call(final TableColumn<EmployeesDTO, String> param) {
                        final TableCell<EmployeesDTO, String> cell = new TableCell<EmployeesDTO, String>() {

                            final Button btn = new Button("Радактировать");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Employees services = getTableView().getItems().get(getIndex()).getEmployees();
                                        editEmployees(services);
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
        Callback<TableColumn<EmployeesDTO, String>, TableCell<EmployeesDTO, String>> deleteFactory
                = //
                new Callback<TableColumn<EmployeesDTO, String>, TableCell<EmployeesDTO, String>>() {
                    @Override
                    public TableCell call(final TableColumn<EmployeesDTO, String> param) {
                        final TableCell<EmployeesDTO, String> cell = new TableCell<EmployeesDTO, String>() {

                            final Button btn = new Button("Удалить");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Employees services = getTableView().getItems().get(getIndex()).getEmployees();
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
        employeesTable.setItems(employeesDTOObservableList);

        addNewEmployees.setOnAction(event -> {
            newEmployees();
        });
        //TODO add search
    };
    private void editEmployees(Employees employees){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("employees/edit.fxml"));
        Stage stage = new Stage();
        Pane pane = null;
        try {
            pane = (Pane)loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        EditEmployeesController controller = (EditEmployeesController) loader.getController();
        controller.initialize(employees,employeesService,windowManager,stage);
        Scene scene = new Scene(pane, 500,500);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }
    private void deleteConfirm(Employees employees){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("employees/delete.fxml"));
        Stage stage = new Stage();
        Pane pane = null;
        try{
            pane = (Pane)loader.load();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        DeleteConfirmController controller = (DeleteConfirmController) loader.getController();
        controller.initialize(employees,employeesService,windowManager,stage);
        Scene scene = new Scene(pane, 500,500);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }
    private void newEmployees(){
        Employees employees = new Employees();
        editEmployees(employees);
    }
}
