package com.example.ogrebeauty;

import com.example.ogrebeauty.controller.helpClass.WindowManager;
import com.example.ogrebeauty.repository.PostgreSQLJDBC;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private WindowManager windowManager;
    @Override
    public void start(Stage stage) throws IOException {
        windowManager = new WindowManager();
        windowManager.setStage(stage);
        windowManager.redirectToEmployeesPage();
    }

    public static void main(String[] args) {
        PostgreSQLJDBC connectionDatabase = new PostgreSQLJDBC();
        connectionDatabase.connectTest();
        //connectionDatabase.autocreateTables();
        launch();
    }
}