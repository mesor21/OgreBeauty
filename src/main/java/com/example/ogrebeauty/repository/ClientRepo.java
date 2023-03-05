package com.example.ogrebeauty.repository;

import com.example.ogrebeauty.entity.Client;

import java.sql.*;

public class ClientRepo{
    public void saveClient(Client client){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ogrebeauty", "postgres", "postgresql");
            Statement stmt = connection.createStatement();
            String sql="INSERT INTO client VALUES("+
                    client.getId().toString()+", '"+
                    client.getFullName()+"', '"+
                    client.getEmail()+"', '"+
                    client.getPhoneNumber()+"', '"+
                    client.getMark()+"')";
            stmt.executeUpdate(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ogrebeauty", "postgres", "postgresql");
            Statement stmt = connection.createStatement();
            String sql = "SELECT id, fullName, email, phoneNumber, mark FROM client WHERE id="+id.toString()+"";
            ResultSet rs = stmt.executeQuery(sql);
            client = new Client(
                    rs.getLong("id"),
                    rs.getString("fullname"),
                    rs.getString("email"),
                    rs.getString("phoneNumber"),
                    rs.getString("mark"));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ogrebeauty", "postgres", "postgresql");
                Statement stmt = connection.createStatement();
                String sql = "DELETE FROM client WHERE id="+id.toString()+"";
                ResultSet rs = stmt.executeQuery(sql);
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
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
}