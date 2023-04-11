package com.example.ogrebeauty;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainPage.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        scene.getStylesheets().add(0, "style.css");
        stage.setTitle("Ogre Beaity");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //PostgreSQLJDBC connectionDatabase = new PostgreSQLJDBC();
        //connectionDatabase.connectTest();
        /*connectionDatabase.autocreateTables();*/
        launch();
    }
}