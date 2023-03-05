package com.example.ogrebeauty.repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}