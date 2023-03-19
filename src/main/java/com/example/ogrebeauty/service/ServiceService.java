package com.example.ogrebeauty.service;

import com.example.ogrebeauty.entity.Service;
import com.example.ogrebeauty.repository.ClientRepo;
import com.example.ogrebeauty.repository.EmployeesRepo;
import com.example.ogrebeauty.repository.ServiceRepo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceService {

    /*Как работать с Data?
    Создавайте в controller новый Data class и вставляйте в нужный метод сервиса через функцию .toString()
    Пример:
    private Data data = new Data();
    data.set...(...);
    service.editService(id, data.toString(), ...) ;
    */

    ServiceRepo serviceRepo;
    ClientRepo clientRepo;
    EmployeesRepo employeesRepo;
    public Service getService(Long id){
        Service service=serviceRepo.findServiceById(id);
        if(service.getClientID()==0) {
            service.setClient(clientRepo.findClientById(Long.parseLong(Integer.toString(service.getClientID()))));
        }
        if(service.getEmployeesID()==0) {
            service.setEmploer(employeesRepo.findEmployeesById(Long.parseLong(Integer.toString(service.getEmployeesID()))));
        }
        return service;
    }
    public void updateService(Service service){
        serviceRepo.deleteServiceById(service.getId(), true);
        serviceRepo.saveService(service);
    }
    public void saveNewService(Service service){
        int id = (int)(long)serviceRepo.getLastId() + 1;
        service.setId(Long.valueOf(id));
        serviceRepo.saveService(service);
    }
}
