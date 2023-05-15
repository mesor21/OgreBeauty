package com.example.ogrebeauty.controller.employeesController;

import com.example.ogrebeauty.controller.helpClass.WindowManager;
import com.example.ogrebeauty.entity.Client;
import com.example.ogrebeauty.entity.Employees;
import com.example.ogrebeauty.service.ClientService;
import com.example.ogrebeauty.service.EmployeesService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteConfirmController {
    private WindowManager windowManager;
    private EmployeesService employeesService;
    private Employees employees;
    @FXML
    private Button deleteTrue;
    @FXML
    private Button deleteFalse;
    public void initialize(Employees employees, EmployeesService employeesService, WindowManager windowManager, Stage stage){
        this.employees = employees;
        this.employeesService = employeesService;
        this.windowManager = windowManager;

        deleteTrue.setOnAction(event -> {
            stage.close();
            employeesService.deleteById(employees.getId()+"");
            try {
                windowManager.redirectToEmployeesPage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        deleteFalse.setOnAction(event -> {
            stage.close();
            try {
                windowManager.redirectToEmployeesPage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
