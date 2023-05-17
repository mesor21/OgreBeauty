package com.example.ogrebeauty.service;

import com.example.ogrebeauty.entity.Service;
import com.example.ogrebeauty.repository.ServiceRepo;

import java.util.Date;
import java.util.List;

public class MainService {
    ServiceRepo serviceRepo;
    public MainService(){
        this.serviceRepo = new ServiceRepo();
    }
    public int summPearDay(Date date){
        List<Service> serviceList = serviceRepo.findByDate(date);
        int summ=0;
        for(int i=0; i<serviceList.size(); i++){
            summ+=serviceList.get(i).getServices().getPrice();
        }
        return summ;
    }
    public List<Service> getServiceByDay(Date date){
        return serviceRepo.findByDate(date);
    }
}
