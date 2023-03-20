package com.example.ogrebeauty.entity;

import java.util.Date;

public class Services {

    private Long id;
    private String serviceType;

    public Services() {
    }

    public Services(String serviceType) {
        this.serviceType = serviceType;
    }

    public Services(int id, String serviceType) {
        this.id = Long.valueOf(id);
        this.serviceType = serviceType;
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
}
