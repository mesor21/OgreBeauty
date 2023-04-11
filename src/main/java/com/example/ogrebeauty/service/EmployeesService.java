package com.example.ogrebeauty.service;

import com.example.ogrebeauty.entity.Employees;
import com.example.ogrebeauty.repository.EmployeesRepo;
import com.example.ogrebeauty.repository.ServiceRepo;

import java.util.List;

public class EmployeesService {
    public EmployeesService() {
        this.employeesRepo = new EmployeesRepo();
        this.serviceRepo = new ServiceRepo();
    }

    EmployeesRepo employeesRepo;
    ServiceRepo serviceRepo;
    public Employees getEmployees(Long id){
        Employees employees=employeesRepo.findEmployeesById(id);
        employees.setService(serviceRepo.getServiceForPeapleService("employees",id));
        return employees;
    }
    public void updateEmployees(Employees employees){
        employeesRepo.deleteEmployeesById(employees.getId(),true);
        employeesRepo.saveEmployees(employees);
    }
    public void saveNewEmployees(Employees employees){
        employees.setId(employeesRepo.getLastId()+1);
        employeesRepo.saveEmployees(employees);
    }
    public void deleteById(String id){
        employeesRepo.deleteEmployeesById(Long.parseLong(id), true);
    }
    //TODO null exception
    public List<Employees> find(String data, String fieldNameFromEntity){
        List<Employees> employees = null;
        if(fieldNameFromEntity.equals("fullName")){
            employees = employeesRepo.findByFullname(data);
        }
        if(fieldNameFromEntity.equals("jobTitle")){
            employees = employeesRepo.findByJobtitle(data);
        }
        return employees;
    }
}
