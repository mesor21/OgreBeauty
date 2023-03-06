package com.example.ogrebeauty.service;

import com.example.ogrebeauty.entity.Client;
import com.example.ogrebeauty.repository.ClientRepo;
import com.example.ogrebeauty.repository.ServiceRepo;

public class ClientService {
    ClientRepo clientRepo;
    ServiceRepo serviceRepo;
    public Client getClient(String id){
        Client client = clientRepo.findClientById(Long.parseLong(id));
        client.setService(serviceRepo.getServiceList("client",Long.parseLong(id)));
        return client;
    }
    public void editClient(String id, String fullName, String email, String phoneNumber, String mark){
        Client client=clientRepo.findClientById(Long.parseLong(id));
        if(fullName.equals("")){}
        else{
            client.setFullName(fullName);
        }
        if(email.equals("")){}
        else{
            client.setEmail(email);
        }
        if(phoneNumber.equals("")){}
        else{
            client.setPhoneNumber(phoneNumber);
        }
        if(mark.equals("")){
            if(client.getMark()==null){
            }else{
                client.setMark(null);
            }
        }
        else{
            client.setMark(mark);
        }
        clientRepo.deleteClientById(client.getId(),true);
        clientRepo.saveClient(client);
    }
    public void saveNewClient(String fullName, String email, String phoneNumber, String mark){
        Client client = new Client(Long.parseLong(Integer.toString(clientRepo.getLastId()+1)),fullName,email,phoneNumber,mark);
        clientRepo.saveClient(client);
    }

    public void deleteClient(String id){
        clientRepo.deleteClientById(Long.parseLong(id), true);
    }
}
