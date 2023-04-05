package com.example.ogrebeauty.controller.DTO;

public class ServiceDTO {
    //объект для записи данных в таблицу. Все данные будут строковыми
    private String employeesName;
    private String clientName;
    private String servicesName;
    private String date;
    private String time;

    public ServiceDTO(String employeesName, String clientName, String servicesName, String date, String time) {
        this.employeesName = employeesName;
        this.clientName = clientName;
        this.servicesName = servicesName;
        this.date = date;
        this.time = time;
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
}