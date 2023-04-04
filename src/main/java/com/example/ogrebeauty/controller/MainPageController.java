package com.example.ogrebeauty.controller;

import com.example.ogrebeauty.entity.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainPageController {

    @FXML
    private TableView<Service> tableView;
    @FXML
    private TableColumn<Service, Integer> idColumn;
    @FXML
    private TableColumn<Service, String> nameColumn;
    @FXML
    private TableColumn<Service, Integer> ageColumn;

    @FXML
    public void initialize() {
        // Устанавливаем значения для столбцов
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        // Добавляем данные в таблицу
        tableView.getItems().add(new (1, "John", 25));
        tableView.getItems().add(new (2, "Jane", 30));
    }



    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}