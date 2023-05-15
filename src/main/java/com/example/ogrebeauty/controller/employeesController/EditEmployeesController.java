package com.example.ogrebeauty.controller.employeesController;

import com.example.ogrebeauty.controller.helpClass.WindowManager;
import com.example.ogrebeauty.entity.Employees;
import com.example.ogrebeauty.service.EmployeesService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditEmployeesController {
    private WindowManager windowManager;
    private EmployeesService employeesService;
    private Employees employees;
    @FXML private TextField fullName;
    @FXML private TextField jobTitle;
    @FXML private Button confirmData;
    @FXML private Button rejectData;
    //TODO
    public void initialize(Employees employees, EmployeesService employeesService, WindowManager windowManager, Stage stage) {
        this.employees = employees;
        this.employeesService = employeesService;
        this.windowManager = windowManager;
        boolean isItNew;
        if(employees.getFullName()!=null){
            fullName.setText(employees.getFullName());
            jobTitle.setText(employees.getJobTitle());
            isItNew = false;
        }
        else{
            isItNew = true;
        }
        confirmData.setOnAction(event -> {
            stage.close();
            employees.setFullName(fullName.getText());
            employees.setJobTitle(jobTitle.getText());
            saveData(employees,isItNew);
            try {
                windowManager.redirectToServicesPage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        rejectData.setOnAction(event -> {
            stage.close();
            try {
                windowManager.redirectToServicesPage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void saveData(Employees employees, boolean isItNew){
        if(isItNew){
            employeesService.saveNewEmployees(employees);
        }
        else{
            employeesService.updateEmployees(employees);
        }
    }
}
