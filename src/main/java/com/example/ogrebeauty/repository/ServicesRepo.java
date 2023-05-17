package com.example.ogrebeauty.repository;

import com.example.ogrebeauty.entity.Services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicesRepo {
    DatabaseInfo databaseInfo = new DatabaseInfo();

    public void save(Services services){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String clientID;
            String employeesID;
            String sql="INSERT INTO services VALUES("+
                    services.getId().toString()+", '"+
                    services.getServiceType()+"', '"+
                    services.getPrice()+"')";
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
    public Services findById(Long id){
        Connection connection = null;
        Services services=null;
        try {
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql = "SELECT id, serviceType, price FROM services WHERE id = "+id.toString()+"";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            services = new Services(
                    rs.getInt("id"),
                    rs.getString("serviceType"),
                    rs.getInt("price")
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
        return services;
    }

    public void delete(Long id, boolean confirm){
        if(confirm){
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
                Statement stmt = connection.createStatement();
                String sql = "DELETE FROM services WHERE id="+id.toString()+"";
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("Delete services. Id:"+id.toString());
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
    public List<Services> getById(Long id){
        List<Services> serviceList=new ArrayList<>();
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql;
            sql="SELECT id, serviceType, price FROM services WHERE id="+id.toString();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                serviceList.add(new Services(rs.getInt("id"),
                        rs.getString("serviceType"),
                        rs.getInt("price")
                ));
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
        long id=0;
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql ="SELECT MAX(id) AS max_id FROM services";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                id = rs.getLong("max_id"); // Retrieve the value using the alias
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
        return id;
    }
    //Search
    public List<Services> findByServiceType(String serviceType){
        Connection connection = null;
        List<Services> client = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql;
            sql="SELECT id, serviceType, price FROM services WHERE serviceType='"+serviceType+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
                client.add(new Services(rs.getInt("id"),
                        rs.getString("serviceType"),
                        rs.getInt("price")
                ));
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
    public List<Services> findByPrice(String price){
        Connection connection = null;
        List<Services> client = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql;
            sql="SELECT id, serviceType, price FROM services WHERE price="+price+"";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
                client.add(new Services(rs.getInt("id"),
                        rs.getString("serviceType"),
                        rs.getInt("price")
                ));
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
    public List<Services> getAll(){
        Connection connection = null;
        List<Services> client = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql;
            sql="SELECT id, serviceType, price FROM services";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
                client.add(new Services(rs.getInt("id"),
                        rs.getString("serviceType"),
                        rs.getInt("price")
                ));
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
}
