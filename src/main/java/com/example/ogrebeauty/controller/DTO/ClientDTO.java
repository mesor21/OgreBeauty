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
}
