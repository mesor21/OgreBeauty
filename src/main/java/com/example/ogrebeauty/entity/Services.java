package com.example.ogrebeauty.entity;

import java.util.Date;

public class Services {

    private Long id;
    private String serviceType;
    private int price;

    public Services(String serviceType) {
        this.serviceType = serviceType;
    }

    public Services(int id, String serviceType, int price) {
        this.id = Long.valueOf(id);
        this.serviceType = serviceType;
        this.price = price;
    }

    public Services(Long id, String serviceType, int price) {
        this.id = id;
        this.serviceType = serviceType;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Long getId() {
        return id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
