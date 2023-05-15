package com.example.ogrebeauty.controller.DTO;

import com.example.ogrebeauty.entity.Employees;
import com.example.ogrebeauty.entity.Service;

import java.util.List;

public class EmployeesDTO {
    private Employees employees;
    private String fullName;
    private String jobTitle;

    public EmployeesDTO(Employees employees) {
        this.fullName = employees.getFullName();
        this.jobTitle = employees.getJobTitle();
        this.employees = employees;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
