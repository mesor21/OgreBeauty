package com.example.ogrebeauty.service;

import com.example.ogrebeauty.entity.Services;
import com.example.ogrebeauty.repository.ServicesRepo;

import java.util.ArrayList;
import java.util.List;

public class ServicesService {
    public ServicesService() {
        this.servicesRepo = new ServicesRepo();
    }

    ServicesRepo servicesRepo;
    public Services getServices(Long id){
        return servicesRepo.findById(id);
    }
    public void updateService(Services services){
        servicesRepo.delete(services.getId(),true);
        servicesRepo.save(services);
    }
    public void saveNewServices(Services services){
        services.setId(servicesRepo.getLastId()+1);
        servicesRepo.save(services);
    }
    public List<Services> findByServiceType(String data, String fieldNameFromEntity){
        List<Services> servicesList =null;
        if(fieldNameFromEntity.equals("serviceType")) {
            servicesList = servicesRepo.findByServiceType(data);
        }
        if(fieldNameFromEntity.equals("price")){
            servicesList = servicesRepo.findByPrice(data);
        }
        return servicesList;
    }
    public void deleteById(Long id){
        servicesRepo.delete(id, true);
    }
}
