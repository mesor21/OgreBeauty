package com.example.ogrebeauty.repository;

import com.example.ogrebeauty.entity.Client;

import java.sql.*;

public class ClientRepo{
    DatabaseInfo databaseInfo = new DatabaseInfo();
    public void saveClient(Client client){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String mark;
            if(client.getMark()==null){
                mark="NULL";
            }
            else{
                mark= client.getMark();
            }

            String sql="INSERT INTO client VALUES("+
                    client.getId().toString()+", '"+
                    client.getFullName()+"', '"+
                    client.getEmail()+"', '"+
                    client.getPhoneNumber()+"', '"+
                    mark+"')";
            stmt.executeUpdate(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public Client findClientById(Long id){
        Connection connection = null;
        Client client=null;
        ServiceRepo serviceRepo=new ServiceRepo();
        try {
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql = "SELECT id, fullName, email, phoneNumber, mark FROM client WHERE id="+id.toString()+"";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            client = new Client(
                    rs.getLong("id"),
                    rs.getString("fullName"),
                    rs.getString("email"),
                    rs.getString("phoneNumber"),
                    rs.getString("mark"));
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return client;
    }
    public void deleteClientById(Long id, boolean confirm){
        if(confirm){
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
                Statement stmt = connection.createStatement();
                String sql = "DELETE FROM client WHERE id="+id.toString()+"";
                ResultSet rs = stmt.executeQuery(sql);
            }
            catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //to-do Сделать нормальный поиск
    public Client findByFullName(String fullName){
        return null;
    }

    public Long getLastId(){
        int id=0;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql ="SELECT MAX(id) FROM client";
            ResultSet rs = stmt.executeQuery(sql);
            id = rs.getInt("id");
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Long.valueOf(id);
    }
}