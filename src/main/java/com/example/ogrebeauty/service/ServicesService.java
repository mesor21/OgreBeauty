package com.example.ogrebeauty.service;

import com.example.ogrebeauty.entity.Services;
import com.example.ogrebeauty.repository.ServicesRepo;

public class ServicesService {
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
}
