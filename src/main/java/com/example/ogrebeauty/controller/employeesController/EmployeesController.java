package com.example.ogrebeauty.controller.employeesController;

import com.example.ogrebeauty.controller.DTO.EmployeesDTO;
import com.example.ogrebeauty.controller.helpClass.Controller;
import com.example.ogrebeauty.controller.helpClass.RedirectController;
import com.example.ogrebeauty.entity.Employees;
import com.example.ogrebeauty.service.EmployeesService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class EmployeesController extends RedirectController implements Controller {
    private EmployeesService employeesService;
    private ObservableList<EmployeesDTO> employeesDTOObservableList;
    @FXML
    public TableView<EmployeesDTO> employeesTable;
    @FXML
    private TableColumn<EmployeesDTO, String> fullName;
    @FXML
    private TableColumn<EmployeesDTO, String> jobTitle;
    @FXML
    private TableColumn<EmployeesDTO, String> editButton;
    @FXML
    private TableColumn<EmployeesDTO, String> deleteButton;

    public  EmployeesController(){
        employeesService = new EmployeesService();
    }

    private ObservableList<EmployeesDTO> setObservableList(List<Employees> services){
        ObservableList<EmployeesDTO> servicesDTOS = FXCollections.observableArrayList();
        for(int i=0; i<services.size(); i++){
            servicesDTOS.add(new EmployeesDTO(services.get(i)));
        }
        return servicesDTOS;
    }
    public void initialize(){

    };
}
