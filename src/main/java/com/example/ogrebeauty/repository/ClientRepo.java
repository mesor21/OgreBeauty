package com.example.ogrebeauty.repository;

import com.example.ogrebeauty.entity.Client;

public class ClientRepo{
    public boolean saveClient(Client client){
        return true;
    }
    public Client findClientById(Long id){
        return null;
    }
    public boolean deleteClientById(Long id, boolean confirm){
        if(confirm){
            return true;
        }
        return false;
    }
    //to-do Сделать нормальный поиск
    public Client findByFullName(String fullName){
        return null;
    }
}