package com.example.ogrebeauty.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLJDBC {
    DatabaseInfo databaseInfo=new DatabaseInfo();
    public void connectTest(){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            System.out.println("Соединение с базой данных успешно установлено.");
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер PostgreSQL не найден.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Не удалось установить соединение с базой данных PostgreSQL.");
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void autocreateTables(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUser(), databaseInfo.getPass());
            System.out.println("Соединение с базой данных успешно установлено.");
            Statement stmt = connection.createStatement();

            //Создание таблицы клиента
            String sql = "CREATE TABLE client " +
                    "(id INTEGER not NULL, " +
                    " fullName VARCHAR(255), " +
                    " email VARCHAR(255), " +
                    " phoneNumber VARCHAR(255), " +
                    " mark VARCHAR(255), " +
                    " PRIMARY KEY( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table client");

            //Создание таблицы Employees
            sql = "CREATE TABLE employees " +
                    "(id INTEGER not NULL, " +
                    " fullName VARCHAR(255), " +
                    " jobTitle VARCHAR(255), " +
                    " PRIMARY KEY( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table employees");

            //Create new table Services
            sql = "CREATE TABLE services " +
                    "(id INTEGER not NULL, " +
                    " serviceType VARCHAR(255))";
            stmt.executeUpdate(sql);
            System.out.println("Created table services");

            //Create table service
            sql = "CREATE TABLE service " +
                    "(id INTEGER not NULL, " +
                    " date VARCHAR(255), " +
                    " servicesID INTEGER, " +
                    " clientID INTEGER, " +
                    " employeesID INTEGER, " +
                    " PRIMARY KEY( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table service");
        }
        catch (SQLException e){
                e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}