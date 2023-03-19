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
    private String serviceType;
    private int employeesID;
    private int clientID;
    private Employees emploer;
    private Client client;

    public Service() {
    }

    public Service(Long id, String dataString, String serviceType, Client client, Employees employees) {
        this.id = id;
        this.serviceType = serviceType;
        try{
            this.data = df.parse(dataString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.emploer=employees;
        this.client=client;
    }

    public Service(Integer id, String dataString, String serviceType, int clientID, int employeesID) {
        this.id = Long.parseLong(id.toString());
        try{
            this.data = df.parse(dataString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.serviceType = serviceType;
        this.clientID = clientID;
        this.employeesID = employeesID;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setData(Date data) {
        this.data = data;
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

    public String getServiceType() {
        return serviceType;
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
                ", serviceType='" + serviceType + '\'' +
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
