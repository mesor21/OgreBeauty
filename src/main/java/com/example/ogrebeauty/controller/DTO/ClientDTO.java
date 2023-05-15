package com.example.ogrebeauty.controller.DTO;

import com.example.ogrebeauty.entity.Client;

public class ClientDTO {
    private Client client;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String mark;

    public ClientDTO(Client client) {
        this.client = client;
        this.fullName = client.getFullName();
        this.email = client.getEmail();
        this.phoneNumber = client.getPhoneNumber();
        this.mark = client.getMark();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
