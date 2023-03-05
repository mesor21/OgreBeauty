package com.example.ogrebeauty.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLJDBC {
    public void connect(){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ogrebeauty", "postgres", "postgresql");
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
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ogrebeauty", "postgres", "postgresql");
            System.out.println("Соединение с базой данных успешно установлено.");
            Statement stmt = connection.createStatement();

            //Создание таблицы клиента
            String sql = "CREATE TABLE client " +
                    "(id INTEGER not NULL, " +
                    " fullName VARCHER(255), " +
                    " email VARCHER(255), " +
                    " phoneNumber VARCHER(255), " +
                    " mark VARCHER(255), " +
                    " PRIMARY KEY( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table client");

            //Создание таблицы Employees
            sql = "CREATE TABLE employees " +
                    "(id INTEGER not NULL, " +
                    " fullName VARCHER(255), " +
                    " jobTitle VARCHER(255), " +
                    " PRIMARY KEY( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table employees");

            //Create table service
            sql = "CREATE TABLE service " +
                    "(id INTEGER not NULL, " +
                    " date VARCHER(255), " +
                    " time VARCHER(255), " +
                    " serviceType VARCHER(255), " +
                    " CONSTRAINT fk_client FOREIGN KEY(client_id) REFERENCES clinet(id), " +
                    " CONSTRAINT fk_employees FOREIGN KEY(employees_id) REFERENCES employees(id), " +
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