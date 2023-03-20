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
        try{
            this.data = df.parse(dataString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.services = services;
        this.emploer = emploer;
        this.client = client;
    }

    public Service(Integer id, String dataString, int clientID, int employeesID) {
        this.id = Long.parseLong(id.toString());
        try{
            this.data = df.parse(dataString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.clientID = clientID;
        this.employeesID = employeesID;
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

    @Override
    public String toString(){
        String out="Service{" +
                "id=" + id +
                ", data=" + data +
                ", servicesID='" + servicesID + '\'' +
                ", emploer=";
        if(emploer==null){
            out+="null";
        }else{
            out+=emploer.toString();
        }
        out+=", client=";
        if(client==null){
            out+="null";
        }
        else{
            out+=client.toString();
        }
        out+="}";
        return out;
    }
}
