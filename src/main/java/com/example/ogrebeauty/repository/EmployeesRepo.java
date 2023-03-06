package com.example.ogrebeauty.repository;

import com.example.ogrebeauty.entity.Employees;

import java.sql.*;

public class EmployeesRepo {
    DatabaseInfo databaseInfo=new DatabaseInfo();
    public void saveEmployees(Employees employees){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql="INSERT INTO employees VALUES("+
                    employees.getId().toString()+", '"+
                    employees.getFullName()+"', '"+
                    employees.getJobTitle()+"')";
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

    public Employees findEmployeesById(Long id){
        Connection connection = null;
        Employees employees=null;
        ServiceRepo serviceRepo = new ServiceRepo();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            Statement stmt = connection.createStatement();
            String sql = "SELECT id, fullName, jobTitle FROM client WHERE id="+id.toString()+"";
            ResultSet rs = stmt.executeQuery(sql);
            employees = new Employees(
                    rs.getLong("id"),
                    rs.getString("fullname"),
                    rs.getString("jobTitle"));
            //Сделано плохо, потому что нет защиты от неправильного id*/
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
        return employees;
    }
    public void deleteEmployeesById(Long id, boolean confirm){
        if(confirm){
            Connection connection = null;
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
                Statement stmt = connection.createStatement();
                String sql = "DELETE FROM employees WHERE id="+id.toString()+"";
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
    public Employees findByFullName(String fullName){
        return null;
    }

    public int getLastId(){
        int id=0;

        return id;
    }
}
