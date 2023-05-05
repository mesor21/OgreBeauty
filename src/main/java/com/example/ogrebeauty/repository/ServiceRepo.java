package com.example.ogrebeauty.repository;

import com.example.ogrebeauty.entity.Service;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
            String sql="INSERT INTO service VALUES("+
                    service.getId().toString()+", '"+
                    (service.getData().getYear()+1900)+"."+(service.getData().getMonth()+1)+"."+service.getData().getDate()+" "+service.getData().getHours()+":"+service.getData().getMinutes()+":"+service.getData().getSeconds()+"', '"+
                    service.getServicesID()+"', '"+
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
        ServicesRepo servicesRepo = new ServicesRepo();
        EmployeesRepo employeesRepo=new EmployeesRepo();
        try {
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql = "SELECT id, date, servicesID, clientID, employeesID FROM service WHERE id = "+id.toString()+"";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            service = new Service(
                    rs.getLong("id"),
                    rs.getString("date"),
                    servicesRepo.findById(rs.getLong("servicesID")),
                    employeesRepo.findEmployeesById(rs.getLong("employeesID")),
                    clientRepo.findClientById(rs.getLong("clientID"))
            );
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

    public List<Service> getServiceForPeapleService(String peaple,Long id){
        List<Service> serviceList=new ArrayList<>();
        Connection connection = null;
        ServicesRepo servicesRepo = new ServicesRepo();
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
                chel="employeesID";
            }
            sql="SELECT id, date, servicesID, clientID, employeesID FROM service WHERE "+chel+"="+id.toString();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                serviceList.add(new Service(
                        rs.getLong("id"),
                        rs.getString("date"),
                        servicesRepo.findById(rs.getLong("servicesID")),
                        employeesRepo.findEmployeesById(rs.getLong("employeesID")),
                        clientRepo.findClientById(rs.getLong("clientID"))
                        )
                );
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
    public List<Service> getServiceList(){
        List<Service> serviceList=new ArrayList<>();
        Connection connection = null;
        ServicesRepo servicesRepo = new ServicesRepo();
        ClientRepo clientRepo = new ClientRepo();
        EmployeesRepo employeesRepo = new EmployeesRepo();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql;
            sql="SELECT id, date, servicesID, clientID, employeesID FROM service";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                serviceList.add(new Service(
                                rs.getLong("id"),
                                rs.getString("date"),
                                servicesRepo.findById(rs.getLong("servicesID")),
                                employeesRepo.findEmployeesById(rs.getLong("employeesID")),
                                clientRepo.findClientById(rs.getLong("clientID"))
                        )
                );
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
    public Long getLastId(){
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
        return Long.valueOf(id);
    }

    //TODO не работает поиск, так как будет ещё время автоматически искаться. Надо будет изменить
    public List<Service> findByDate(Date date){
        Connection connection = null;
        ServicesRepo servicesRepo = new ServicesRepo();
        ClientRepo clientRepo = new ClientRepo();
        EmployeesRepo employeesRepo = new EmployeesRepo();
        List<Service> service = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql;
            sql="SELECT id, date, servicesID, clientID, employeesID FROM service WHERE date BETWEEN '"+ (date.getYear()+1900)+"."+(date.getMonth()+1)+"."+date.getDate()+" 00:00:00" +"' AND '"+date.getYear()+1900+"."+date.getMonth()+1+"."+date.getDate()+"23:59:59'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
                service.add(
                        new Service(
                                rs.getLong("id"),
                                rs.getString("date"),
                                servicesRepo.findById(rs.getLong("servicesID")),
                                employeesRepo.findEmployeesById(rs.getLong("employeesID")),
                                clientRepo.findClientById(rs.getLong("clientID"))
                        )
                );
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
        return service;
    }
    public List<Service> findByClientID(Long clientID){
        Connection connection = null;
        ServicesRepo servicesRepo = new ServicesRepo();
        ClientRepo clientRepo = new ClientRepo();
        EmployeesRepo employeesRepo = new EmployeesRepo();
        List<Service> service = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql;
            sql="SELECT id, date, servicesID, clientID, employeesID FROM service WHERE clientID="+clientID+"";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
                service.add(
                        new Service(
                                rs.getLong("id"),
                                rs.getString("date"),
                                servicesRepo.findById(rs.getLong("servicesID")),
                                employeesRepo.findEmployeesById(rs.getLong("employeesID")),
                                clientRepo.findClientById(rs.getLong("clientID"))
                        )
                );
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
        return service;
    }
    public List<Service> findByEmployeesID(Long servicesID){
        Connection connection = null;
        ServicesRepo servicesRepo = new ServicesRepo();
        ClientRepo clientRepo = new ClientRepo();
        EmployeesRepo employeesRepo = new EmployeesRepo();
        List<Service> service = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql;
            sql="SELECT id, date, servicesID, clientID, employeesID FROM service WHERE employeesID="+servicesID+"";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
                service.add(
                        new Service(
                                rs.getLong("id"),
                                rs.getString("date"),
                                servicesRepo.findById(rs.getLong("servicesID")),
                                employeesRepo.findEmployeesById(rs.getLong("employeesID")),
                                clientRepo.findClientById(rs.getLong("clientID"))
                        )
                );
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
        return service;
    }
    public List<Service> findByServicesID(Long servicesID){
        Connection connection = null;
        ServicesRepo servicesRepo = new ServicesRepo();
        ClientRepo clientRepo = new ClientRepo();
        EmployeesRepo employeesRepo = new EmployeesRepo();
        List<Service> service = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql;
            sql="SELECT id, date, servicesID, clientID, employeesID FROM service WHERE servicesID="+servicesID+"";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
                service.add(
                        new Service(
                                rs.getLong("id"),
                                rs.getString("date"),
                                servicesRepo.findById(rs.getLong("servicesID")),
                                employeesRepo.findEmployeesById(rs.getLong("employeesID")),
                                clientRepo.findClientById(rs.getLong("clientID"))
                        )
                );
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
        return service;
    }
}
