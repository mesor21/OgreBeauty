package com.example.ogrebeauty.service;

import com.example.ogrebeauty.entity.Client;
import com.example.ogrebeauty.repository.ClientRepo;
import com.example.ogrebeauty.repository.ServiceRepo;
import org.w3c.dom.ls.LSException;

import java.util.List;

public class ClientService {
    public ClientService() {
        this.clientRepo = new ClientRepo();
        this.serviceRepo = new ServiceRepo();
    }

    ClientRepo clientRepo;
    ServiceRepo serviceRepo;
    public Client getClient(Long id){
        Client client = clientRepo.findClientById(id);
        client.setService(serviceRepo.getServiceForPeapleService("client",id));
        return client;
    }
    public void updateClient(Client client){
        clientRepo.deleteClientById(client.getId(),true);
        clientRepo.saveClient(client);
    }
    public void saveNewClient(Client client){
        client.setId(clientRepo.getLastId() + 1);
        clientRepo.saveClient(client);
    }

    public void deleteClient(String id){
        clientRepo.deleteClientById(Long.parseLong(id), true);
    }

    //TODO null exception
    public List<Client> find(String data, String fieldNameFromEntity){
        List<Client> clientList = null;
        if(fieldNameFromEntity.equals("fullname")) {
            clientList= clientRepo.findByFullname(data);
        }
        if(fieldNameFromEntity.equals("email")) {
            clientList = clientRepo.findEmail(data);
        }
        if(fieldNameFromEntity.equals("phoneNumber")) {
            clientList = clientRepo.findByPhoneNumber(data);
        }
        if(fieldNameFromEntity.equals("mark")) {
            return clientRepo.findByMark(data);
        }
        return clientList;
    }
}
