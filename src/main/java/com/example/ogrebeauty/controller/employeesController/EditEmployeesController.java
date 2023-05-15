package com.example.ogrebeauty.controller.employeesController;

import com.example.ogrebeauty.controller.helpClass.WindowManager;
import com.example.ogrebeauty.entity.Employees;
import com.example.ogrebeauty.service.EmployeesService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditEmployeesController {
    private WindowManager windowManager;
    private EmployeesService employeesService;
    private Employees employees;
    @FXML private TextField fullName;
    @FXML private TextField jobTitle;
    @FXML private Button confirmData;
    @FXML private Button rejectData;
    //TODO
}
