package com.example.ogrebeauty.entity;


import java.util.List;

public class Client {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String mark;

    private List<Service> service;

    public Client() {
    }

    public Client(Long id, String fullName, String email, String phoneNumber, String mark) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.mark = mark;
    }

    public Long getId() {
        return id;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMark() {
        return mark;
    }
}
