package com.example.ogrebeauty.controller;

import com.example.ogrebeauty.controller.DTO.ServiceDTO;
import com.example.ogrebeauty.entity.Service;
import com.example.ogrebeauty.service.ServiceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ServiceController extends MainPageController   {

    private ServiceService serviceService;

    public ServiceController(){
        serviceService = new ServiceService();
    }

    @FXML
    private TableView<ServiceDTO> tableView;
    @FXML
    private TableColumn<ServiceDTO, String> employeesName;
    @FXML
    private TableColumn<ServiceDTO, String> clientName;
    @FXML
    private TableColumn<ServiceDTO, String> servicesName;
    @FXML
    private TableColumn<ServiceDTO, String> date;
    @FXML
    private TableColumn<ServiceDTO, String> time;
    @FXML
    public ObservableList<ServiceDTO> setTableData(List<Service> serviceList) { //TODO эта функция будет только выводить данные в табилцу. На вход подаётся лист. Надо бы так для всех контроллеров сделать
        // Устанавливаем значения для столбцов
        employeesName.setCellValueFactory(new PropertyValueFactory<>("employeesName"));
        clientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        servicesName.setCellValueFactory(new PropertyValueFactory<>("servicesName"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        ObservableList<ServiceDTO> observableList = FXCollections.observableArrayList();
        //observableList.add(new ServiceDTO(Long.valueOf(1),"Dmitry","Dmitry","test","12.06.2023","15:00"));

        for(int i=0; i<serviceList.size(); i++){
            observableList.add(new ServiceDTO(
                    serviceList.get(i).getId(),
                    serviceList.get(i).getEmploer().getFullName(),
                    serviceList.get(i).getClient().getFullName(),
                    serviceList.get(i).getServices().getServiceType(),
                    serviceList.get(i).getData().toString(), //TODO разделить дату и время
                    serviceList.get(i).getData().toString()
            ));
        }
        return observableList;
    }

    @FXML
    public void initialize(){ //TODO Можно объеденить поиск и выдачу информации по дефолту
        List<Service> serviceList;
        serviceList = serviceService.getListService();
        tableView.setItems(setTableData(serviceList));
        /*if(){//Если у тебя только вызвали весь список
            serviceList=serviceService.getListService();
        }
        if(){
        }//Если поиск там по определённым параметрам*/
    }
}
