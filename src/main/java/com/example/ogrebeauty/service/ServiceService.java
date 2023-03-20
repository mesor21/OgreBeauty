package com.example.ogrebeauty.service;

import com.example.ogrebeauty.entity.Client;
import com.example.ogrebeauty.entity.Employees;
import com.example.ogrebeauty.entity.Service;
import com.example.ogrebeauty.entity.Services;
import com.example.ogrebeauty.repository.ClientRepo;
import com.example.ogrebeauty.repository.EmployeesRepo;
import com.example.ogrebeauty.repository.ServiceRepo;
import com.example.ogrebeauty.repository.ServicesRepo;

import java.util.List;

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
    ServicesRepo servicesRepo;
    public Service getService(Long id){
        Service service=serviceRepo.findServiceById(id);
        if(service.getServicesID()==0){
            service.setServices(servicesRepo.findById(Long.valueOf(service.getServicesID())));
        }
        if(service.getClientID()==0){
            service.setClient(clientRepo.findClientById(Long.parseLong(Integer.toString(service.getClientID()))));
        }
        if(service.getEmployeesID()==0){
            service.setEmploer(employeesRepo.findEmployeesById(Long.parseLong(Integer.toString(service.getEmployeesID()))));
        }
        return service;
    }
    public void updateService(Service service){
        serviceRepo.deleteServiceById(service.getId(), true);
        serviceRepo.saveService(service);
    }
    public void saveNewService(Service service){
        service.setId(serviceRepo.getLastId() + 1);
        serviceRepo.saveService(service);
    }
    //TODO null exception
    public List<Service> find(String data, String fieldNameFromEntity){
        List<Service> serviceList = null;
        if(fieldNameFromEntity.equals("clientFullname")){
            List<Client> clientList= clientRepo.findByFullname(data);
            for(int i=0; i<clientList.size(); i++){
                serviceList.addAll(serviceRepo.findByClientID(clientList.get(i).getId()));
            }
        }
        if(fieldNameFromEntity.equals("employeeFullname")){
            List<Employees> employeesList = employeesRepo.findByFullname(data);
            for(int i=0; i<employeesList.size(); i++){
                serviceList.addAll(serviceRepo.findByEmployeesID(employeesList.get(i).getId()));
            }
        }
        if(fieldNameFromEntity.equals("serviceType")){
            List<Services> services = servicesRepo.findByServiceType(data);
            for(int i=0; i<services.size(); i++){
                serviceList.addAll(serviceRepo.findByServicesID(services.get(i).getId()));
            }
        }
        return serviceList;
    }
}
