package com.example.ogrebeauty.repository;

import com.example.ogrebeauty.entity.Employees;

public class EmployeesRepo {
    public boolean saveClient(Employees employees){
        return true;
    }
    public Employees findClientById(Long id){
        return null;
    }
    public boolean deleteClientById(Long id, boolean confirm){
        if(confirm){
            return true;
        }
        return false;
    }
    //to-do Сделать нормальный поиск
    public Employees findByFullName(String fullName){
        return null;
    }
}
