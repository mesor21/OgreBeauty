package com.example.ogrebeauty.controller;

import com.example.ogrebeauty.controller.DTO.ServiceDTO;
import com.example.ogrebeauty.entity.Employees;
import com.example.ogrebeauty.service.EmployeesService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EmployeesController extends MainPageController {
    private EmployeesService employeesService;
    public  EmployeesController(){
        employeesService = new EmployeesService();
    }
    @FXML
    private TableView<Employees> employeesTable;
    @FXML
    private TableColumn<Employees, String> fullName;
    @FXML
    private TableColumn<Employees, String> jobTitle;
    @FXML
    private TableColumn<Employees, Long> buttons;

}
