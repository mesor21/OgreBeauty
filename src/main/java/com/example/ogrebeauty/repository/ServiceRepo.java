package com.example.ogrebeauty.repository;

import com.example.ogrebeauty.entity.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepo {
    DatabaseInfo databaseInfo = new DatabaseInfo();
    public void saveService(Service service){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String clientID;
            String employeesID;
            if(service.getClient()==null){
                clientID="0";
            }
            else{
                clientID=service.getClient().getId().toString();
            }
            if(service.getEmploer()==null){
                employeesID="0";
            }
            else{
                employeesID = service.getEmploer().getId().toString();
            }
            String data = service.getData().getDay()+"/"+
                    service.getData().getMonth()+"/"+
                    service.getData().getYear()+" "+
                    service.getData().getMinutes();

            System.out.println(data);

            String sql="INSERT INTO service VALUES("+
                    service.getId().toString()+", '"+
                    data+"', '"+
                    service.getServiceType()+"', '"+
                    clientID+"', '"+
                    employeesID+"')";
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
    public Service findServiceById(Long id){
        Connection connection = null;
        Service service=null;
        ClientRepo clientRepo=new ClientRepo();
        EmployeesRepo employeesRepo=new EmployeesRepo();
        try {
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql = "SELECT id, date, serviceType, clientID, employeesID FROM service WHERE id = "+id.toString()+"";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            service = new Service(
                    rs.getInt("id"),
                    rs.getString("date"),
                    rs.getString("serviceType"),
                    rs.getInt("clientID"),
                    rs.getInt("employeesID"));
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
        return service;
    }
    public void deleteServiceById(Long id, boolean confirm){
        if(confirm){
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
                Statement stmt = connection.createStatement();
                String sql = "DELETE FROM service WHERE id="+id.toString()+"";
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("Delete service. Id:"+id.toString());
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

    //to-do сделать поиск клиенту
    public List<Service> getServiceList(String peaple,Long id){
        List<Service> serviceList=new ArrayList<>();
        Connection connection = null;
        ClientRepo clientRepo = new ClientRepo();
        EmployeesRepo employeesRepo = new EmployeesRepo();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql;
            String chel=new String();
            if(peaple.equals("client")){
                chel="clientID";
            }
            if(peaple.equals("employees")){
                chel="employeerID";
            }
            sql="SELECT id, date, serviceType, clientID, employeerID FROM service WHERE "+chel+"="+id.toString();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                serviceList.add(new Service(rs.getLong("id"),
                        rs.getString("date"),
                        rs.getString("serviceType"),
                        clientRepo.findClientById(rs.getLong("clientID")),
                        employeesRepo.findEmployeesById(rs.getLong("id"))));
            }
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
        return serviceList;
    }
    public int getLastId(){
        int id=0;
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql ="SELECT MAX(id) FROM service";
            ResultSet rs = stmt.executeQuery(sql);
            id = rs.getInt("id");
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
        return id;
    }
}
