package com.example.ogrebeauty.entity;


import java.sql.Time;
import java.util.Date;

public class Service {
    private Long id;
    private Date data;
    private Time time;
    private String serviceType;
    private Employees emploer;
    private Client client;

    public Service() {
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setEmploer(Employees emploer) {
        this.emploer = emploer;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public Time getTime() {
        return time;
    }

    public String getServiceType() {
        return serviceType;
    }

    public Employees getEmploer() {
        return emploer;
    }

    public Client getClient() {
        return client;
    }
}
