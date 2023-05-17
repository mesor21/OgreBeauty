package com.example.ogrebeauty.controller.DTO;

import com.example.ogrebeauty.entity.Service;

import java.util.Date;

public class ServiceDTO {
    //объект для записи данных в таблицу. Все данные будут строковыми
    private Long id;
    private String employeesName;
    private String clientName;
    private String servicesName;
    private String date;
    private String time;
    private Date dateDate;

    public ServiceDTO() {
    }

    public ServiceDTO(Service service) {
        this.id = service.getId();
        this.employeesName = service.getEmploer().getFullName();
        this.clientName = service.getClient().getFullName();
        this.servicesName = service.getServices().getServiceType();
        this.date = service.getData().getDate()+"."+service.getData().getMonth()+"."+service.getData().getYear();
        this.time = service.getData().getHours()+":"+service.getData().getMinutes();
        this.dateDate = service.getData();
    }

    public ServiceDTO(Long id, String employeesName, String clientName, String servicesName, String date, String time, Date dateDate) {
        this.id = id;
        this.employeesName = employeesName;
        this.clientName = clientName;
        this.servicesName = servicesName;
        this.date = date;
        this.time = time;
        this.dateDate = dateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployeesName(String employeesName) {
        this.employeesName = employeesName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmployeesName() {
        return employeesName;
    }

    public String getClientName() {
        return clientName;
    }

    public String getServicesName() {
        return servicesName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Date getDateDate() {
        return dateDate;
    }

    public void setDateDate(Date dateDate) {
        this.dateDate = dateDate;
    }
}