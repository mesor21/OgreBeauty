package com.example.ogrebeauty.controller;

import com.example.ogrebeauty.entity.Service;
import com.example.ogrebeauty.service.ServiceService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ServiceController {

    private ServiceService serviceService;
    private ServiceController(){
        serviceService = new ServiceService();
    }

    @FXML
    private TableView<Service> tableView;
    @FXML
    private TableColumn<Service, Integer> idColumn;
    @FXML
    private TableColumn<Service, String> nameColumn;
    @FXML
    private TableColumn<Service, Integer> ageColumn;

    @FXML
    public void whatSetInTable(){ //TODO Можно объеденить поиск и выдачу информации по дефолту
        List<Service> serviceList;
        if(){//Если у тебя только вызвали весь список
            serviceList=serviceService.getListService();
        }
        if(){
            
        }//Если поиск там по определённым параметрам
        setTableData(serviceList);
    }

    @FXML
    public void setTableData(List<Service> serviceList) { //TODO эта функция будет только выводить данные в табилцу. На вход подаётся лист. Надо бы так для всех контроллеров сделать
        // Устанавливаем значения для столбцов
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        while(serviceList.){

        }
    }

}
