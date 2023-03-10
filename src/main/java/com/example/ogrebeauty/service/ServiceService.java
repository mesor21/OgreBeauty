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
    public Service getService(String id){
        Service service=serviceRepo.findServiceById(Long.parseLong(id));
        if(service.getClientID()==0) {
            service.setClient(clientRepo.findClientById(Long.parseLong(Integer.toString(service.getClientID()))));
        }
        if(service.getEmployeesID()==0) {
            service.setEmploer(employeesRepo.findEmployeesById(Long.parseLong(Integer.toString(service.getEmployeesID()))));
        }
        return service;
    }
    public void editService(String id, String data, String serviceType, String clientID, String employeesID){
        Service service=serviceRepo.findServiceById(Long.parseLong(id));
        DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Date date;
        if(data.equals("")){}
        else{
            try {
                service.setData(date = df.parse(data));
            } catch (ParseException e) {
                e.printStackTrace();
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
