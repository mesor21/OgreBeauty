package com.example.ogrebeauty.entity;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Service {
    public static final DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
    private Long id;
    private Date data;
    private Services services;
    private int servicesID;
    private int employeesID;
    private int clientID;
    private Employees emploer;
    private Client client;

    public Service() {
    }

    public Service(Long id, String dataString, Services services, Employees emploer, Client client) {
        this.id = id;
        SimpleDateFormat parser = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
        try {
            this.data = parser.parse(dataString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.services = services;
        this.emploer = emploer;
        this.client = client;
    }

    public Service(Long id, Date data, Services services, Employees emploer, Client client) {
        this.id = id;
        this.data = data;
        this.services = services;
        this.emploer = emploer;
        this.client = client;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public int getServicesID() {
        return servicesID;
    }

    public void setServicesID(int servicesID) {
        this.servicesID = servicesID;
    }

    public void setEmployeesID(int employeesID) {
        this.employeesID = employeesID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setData(Date data) {
        this.data = data;
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


    public Employees getEmploer() {
        return emploer;
    }

    public Client getClient() {
        return client;
    }

    public int getEmployeesID() {
        return employeesID;
    }

    public int getClientID() {
        return clientID;
    }


}
