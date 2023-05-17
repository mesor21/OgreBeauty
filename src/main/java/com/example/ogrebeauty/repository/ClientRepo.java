package com.example.ogrebeauty.repository;

import com.example.ogrebeauty.entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public Long getLastId(){
        long id=0;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql ="SELECT MAX(id) AS max_id FROM client";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                id = rs.getLong("max_id"); // Retrieve the value using the alias
            }
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
        return id;
    }
    //Search
    public List<Client> findByFullname(String fullname){
        Connection connection = null;
        List<Client> client = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql;
            sql="SELECT id, fullName, email, phoneNumber, mark FROM client WHERE fullName='"+fullname+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
                client.add(new Client(
                        rs.getLong("id"),
                        rs.getString("fullName"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getString("mark")));
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    public List<Client> findEmail(String email){
        Connection connection = null;
        List<Client> client = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql;
            sql="SELECT id, fullName, email, phoneNumber, mark FROM client WHERE email='"+email+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
                client.add(new Client(
                        rs.getLong("id"),
                        rs.getString("fullName"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getString("mark")));
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    public List<Client> findByPhoneNumber(String phoneNumber){
        Connection connection = null;
        List<Client> client = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql;
            sql="SELECT id, fullName, email, phoneNumber, mark FROM client WHERE phoneNumber='"+phoneNumber+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
                client.add(new Client(
                        rs.getLong("id"),
                        rs.getString("fullName"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getString("mark")));
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    public List<Client> findByMark(String mark){
        Connection connection = null;
        List<Client> client = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql;
            sql="SELECT id, fullName, email, phoneNumber, mark FROM client WHERE mark='"+mark+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
                client.add(new Client(
                        rs.getLong("id"),
                        rs.getString("fullName"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getString("mark")));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return client;
    }
    public List<Client> getAll(){
        Connection connection = null;
        List<Client> client = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql;
            sql="SELECT id, fullName, email, phoneNumber, mark FROM client";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
                client.add(new Client(
                        rs.getLong("id"),
                        rs.getString("fullName"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getString("mark")));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return client;
    }
}