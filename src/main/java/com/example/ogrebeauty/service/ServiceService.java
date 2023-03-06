package com.example.ogrebeauty.service;

import com.example.ogrebeauty.entity.Client;
import com.example.ogrebeauty.entity.Employees;
import com.example.ogrebeauty.entity.Service;
import com.example.ogrebeauty.repository.ClientRepo;
import com.example.ogrebeauty.repository.EmployeesRepo;
import com.example.ogrebeauty.repository.ServiceRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ServiceService {
    ServiceRepo serviceRepo;
    ClientRepo clientRepo;
    EmployeesRepo employeesRepo;
    public Service getService(String id){
        Service service=serviceRepo.findServiceById(Long.parseLong(id));
        service.setClient( clientRepo.findClientById( Long.parseLong( Integer.toString( service.getClientID()))));
        service.setEmploer(employeesRepo.findEmployeesById(Long.parseLong(Integer.toString(service.getEmployeesID()))));
        return service;
    }
    public void editService(String id, String data, String serviceType, String clientID, String employeesID){
        Service service=serviceRepo.findServiceById(Long.parseLong(id));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
        if(data.equals("")){}
        else{
            try{
                service.setData(formatter.parse(data));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        if(serviceType.equals("")){}
        else{
            service.setServiceType(serviceType);
        }
        if(clientID.equals("")){}
        else{
            service.setClient(clientRepo.findClientById(Long.parseLong(clientID)));
        }
        if(employeesID.equals("")){}
        else{
            service.setEmploer(employeesRepo.findEmployeesById(Long.parseLong(employeesID)));
        }
    }
    public void saveNewService(String data, String serviceType, String clientID, String employeesID){
        Service service = new Service(Long.parseLong(Integer.toString(serviceRepo.getLastId()+1)), data, serviceType,clientRepo.findClientById(Long.parseLong(clientID)),employeesRepo.findEmployeesById(Long.parseLong(employeesID)));
        serviceRepo.saveService(service);
    }
}
