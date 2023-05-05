package com.example.ogrebeauty;

import com.example.ogrebeauty.controller.mainController.RedirectController;
import com.example.ogrebeauty.repository.PostgreSQLJDBC;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        RedirectController mainPageController = new RedirectController();
        mainPageController.setStage(stage);
        mainPageController.setScene("mainPage/mainPage.fxml");
    }

    public static void main(String[] args) {
        PostgreSQLJDBC connectionDatabase = new PostgreSQLJDBC();
        connectionDatabase.connectTest();
        //connectionDatabase.autocreateTables();
        launch();
    }
}