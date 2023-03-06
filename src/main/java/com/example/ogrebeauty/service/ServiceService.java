package com.example.ogrebeauty.service;

import com.example.ogrebeauty.entity.Client;
import com.example.ogrebeauty.entity.Employees;
import com.example.ogrebeauty.entity.Service;
import com.example.ogrebeauty.repository.ClientRepo;
import com.example.ogrebeauty.repository.EmployeesRepo;
import com.example.ogrebeauty.repository.ServiceRepo;

public class ServiceService {
    ServiceRepo serviceRepo;
    ClientRepo clientRepo;
    EmployeesRepo employeesRepo;
    public Service getService(String id){
        Service service=new Service();
        service=serviceRepo.findServiceById(Long.parseLong(id));
        return service;
    }
    public void saveService(String id, String data, String serviceType, String clientID, String employeesID){
        //TODO заменить параметр id на поиск последнего элемента в таблице + 1
        Service service = new Service(Long.parseLong(id), data, serviceType,clientRepo.findClientById(Long.parseLong(clientID)),employeesRepo.findEmployeesById(Long.parseLong(employeesID)));
        serviceRepo.saveService(service);
    }
}
