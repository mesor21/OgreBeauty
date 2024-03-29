package com.example.ogrebeauty.service;

import com.example.ogrebeauty.entity.Client;
import com.example.ogrebeauty.entity.Employees;
import com.example.ogrebeauty.entity.Service;
import com.example.ogrebeauty.entity.Services;
import com.example.ogrebeauty.repository.ClientRepo;
import com.example.ogrebeauty.repository.EmployeesRepo;
import com.example.ogrebeauty.repository.ServiceRepo;
import com.example.ogrebeauty.repository.ServicesRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceService {
    public ServiceService() {
        this.serviceRepo = new ServiceRepo();
        this.clientRepo = new ClientRepo();
        this.employeesRepo = new EmployeesRepo();
        this.servicesRepo = new ServicesRepo();
    }

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
    public List<Service> getListService(){
        return serviceRepo.getServiceList();
    }
    public List<Service> find(String data, String fieldNameFromEntity){
        List<Service> serviceList = new ArrayList<>();
        if(fieldNameFromEntity.equals("clientFullname")){
            List<Client> clientList = clientRepo.findByFullname(data);
            for (Client client : clientList) {
                serviceList.addAll(serviceRepo.findByClientID(client.getId()));
            }
        }
        if(fieldNameFromEntity.equals("employeeFullname")){
            List<Employees> employeesList = employeesRepo.findByFullname(data);
            for (Employees employees : employeesList) {
                serviceList.addAll(serviceRepo.findByEmployeesID(employees.getId()));
            }
        }
        if(fieldNameFromEntity.equals("serviceType")){
            List<Services> services = servicesRepo.findByServiceType(data);
            for (Services service : services) {
                serviceList.addAll(serviceRepo.findByServicesID(service.getId()));
            }
        }
        return serviceList;
    }
    public List<Service> getServiceByDay(Date date){
        return serviceRepo.findByDate(date);
    }
    public void delete(Long id){
        serviceRepo.deleteServiceById(id,true);
    }
    public List<Service> getAll(){
        return serviceRepo.getAll();
    }

}
