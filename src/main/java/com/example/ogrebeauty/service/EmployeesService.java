package com.example.ogrebeauty.service;

import com.example.ogrebeauty.entity.Employees;
import com.example.ogrebeauty.repository.EmployeesRepo;
import com.example.ogrebeauty.repository.ServiceRepo;

public class EmployeesService {
    EmployeesRepo employeesRepo;
    ServiceRepo serviceRepo;
    public Employees getEmployees(Long id){
        Employees employees=employeesRepo.findEmployeesById(id);
        employees.setService(serviceRepo.getServiceList("employees",id));
        return employees;
    }
    public void updateEmployees(Employees employees){
        employeesRepo.deleteEmployeesById(employees.getId(),true);
        employeesRepo.saveEmployees(employees);
    }
    public void saveNewEmployees(Employees employees){
        int id = (int)(long) employeesRepo.getLastId() + 1;
        employees.setId(Long.valueOf(id));
        employeesRepo.saveEmployees(employees);
    }
    public void deleteById(String id){
        employeesRepo.deleteEmployeesById(Long.parseLong(id), true);
    }
}
