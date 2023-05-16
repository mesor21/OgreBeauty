package com.example.ogrebeauty.repository;

import com.example.ogrebeauty.entity.Employees;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeesRepo {
    DatabaseInfo databaseInfo=new DatabaseInfo();
    public void saveEmployees(Employees employees){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql="INSERT INTO employees VALUES("+
                    employees.getId().toString()+", '"+
                    employees.getFullName()+"', '"+
                    employees.getJobTitle()+"')";
            stmt.executeUpdate(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Employees findEmployeesById(Long id){
        Connection connection = null;
        Employees employees=null;
        ServiceRepo serviceRepo = new ServiceRepo();
        try {
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql = "SELECT id, fullName, jobTitle FROM employees WHERE id = "+id.toString()+"";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            employees = new Employees(
                    rs.getLong("id"),
                    rs.getString("fullName"),
                    rs.getString("jobTitle"));
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
        return employees;
    }
    public void deleteEmployeesById(Long id, boolean confirm){
        if(confirm){
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
                Statement stmt = connection.createStatement();
                String sql = "DELETE FROM employees WHERE id="+id.toString()+"";
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("Delete employees. Id: "+id.toString());
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
        int id=0;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql ="SELECT MAX(id) FROM employees";
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
    //Search
    public List<Employees> findByFullname(String fullname){
        Connection connection = null;
        List<Employees> employees = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql;
            sql="SELECT id, fullName, jobTitle FROM employees WHERE fullName='"+fullname+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
                employees.add(new Employees(
                        rs.getLong("id"),
                        rs.getString("fullName"),
                        rs.getString("jobTitle")));
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
        return employees;
    }
    public List<Employees> findByJobtitle(String jobtitle){
        Connection connection = null;
        List<Employees> employees = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql;
            sql="SELECT id, fullName, jobTitle FROM employees WHERE jobTitle='"+jobtitle+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
                employees.add(new Employees(
                        rs.getLong("id"),
                        rs.getString("fullName"),
                        rs.getString("jobTitle")));
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
        return employees;
    }
    public List<Employees> getAll(){
        Connection connection = null;
        List<Employees> employees = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql;
            sql="SELECT id, fullName, jobTitle FROM employees";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
                employees.add(new Employees(
                        rs.getLong("id"),
                        rs.getString("fullName"),
                        rs.getString("jobTitle")));
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
        return employees;
    }
}
