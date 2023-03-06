package com.example.ogrebeauty.service;

import com.example.ogrebeauty.entity.Employees;
import com.example.ogrebeauty.repository.EmployeesRepo;
import com.example.ogrebeauty.repository.ServiceRepo;

public class EmployeesService {
    EmployeesRepo employeesRepo;
    ServiceRepo serviceRepo;
    public Employees getEmployees(String id){
        Employees employees=employeesRepo.findEmployeesById(Long.parseLong(id));
        employees.setService(serviceRepo.getServiceList("employees",Long.parseLong(id)));
        return employees;
    }
    public void editEmployees(String id, String fullName, String jobTitle){
        Employees employees=employeesRepo.findEmployeesById(Long.parseLong(id));
        if(fullName.equals("")){}
        else{
            employees.setFullName(fullName);
        }
        if(jobTitle.equals("")){}
        else{
            employees.setJobTitle(jobTitle);
        }
        employeesRepo.deleteEmployeesById(Long.parseLong(id),true);
        employeesRepo.saveEmployees(employees);
    }
    public void saveEmployees(String id, String fullName, String jobTitle){
        //TODO заменить параметр id на поиск последнего элемента в таблице + 1
        Employees employees = new Employees(Long.parseLong(id), fullName, jobTitle);
        employeesRepo.saveEmployees(employees);
    }
    public void deleteById(String id){
        employeesRepo.deleteEmployeesById(Long.parseLong(id), true);
    }
}
