package com.example.ogrebeauty.entity;

import java.util.List;

public class Employees {
    private Long id;
    private String fullName;
    private String jobTitle;
    private List<Service> service;

    public Employees() {
    }

    public Employees(Long id, String fullName, String jobTitle) {
        this.id=id;
        this.fullName = fullName;
        this.jobTitle = jobTitle;
    }

    public Employees(Long id, String fullName, String jobTitle, List<Service> service) {
        this.id = id;
        this.fullName = fullName;
        this.jobTitle = jobTitle;
        this.service = service;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public List<Service> getService() {
        return service;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
