package com.example.ogrebeauty.repository;

public class DatabaseInfo {
    private String url = "jdbc:postgresql://localhost:5432/ogrebeauty";
    private String user = "postgres";
    private String pass = "postgresql";

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
}
