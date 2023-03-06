package com.example.ogrebeauty.entity;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Service {
    private Long id;
    private Date data;
    private String serviceType;
    private Employees emploer;
    private Client client;

    public Service() {
    }

    public Service(Long id, String data, String serviceType, Client client, Employees employees) {
        this.id = id;
        this.serviceType = serviceType;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
        try{
            this.data = formatter.parse(data);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.emploer=employees;
        this.client=client;
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
