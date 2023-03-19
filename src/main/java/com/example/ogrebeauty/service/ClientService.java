package com.example.ogrebeauty.service;

import com.example.ogrebeauty.entity.Client;
import com.example.ogrebeauty.repository.ClientRepo;
import com.example.ogrebeauty.repository.ServiceRepo;

public class ClientService {
    ClientRepo clientRepo;
    ServiceRepo serviceRepo;
    public Client getClient(Long id){
        Client client = clientRepo.findClientById(id);
        client.setService(serviceRepo.getServiceList("client",id));
        return client;
    }
    public void updateClient(Client client){
        clientRepo.deleteClientById(client.getId(),true);
        clientRepo.saveClient(client);
    }
    public void saveNewClient(Client client){
        int id = (int)(long)clientRepo.getLastId() + 1;
        client.setId(Long.valueOf(id));
        clientRepo.saveClient(client);
    }

    public void deleteClient(String id){
        clientRepo.deleteClientById(Long.parseLong(id), true);
    }
}
