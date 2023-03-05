package com.example.ogrebeauty;

import com.example.ogrebeauty.repository.PostgreSQLJDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Ogre Beaity");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        PostgreSQLJDBC connectionDatabase = new PostgreSQLJDBC();
        connectionDatabase.connectTest();
        launch();
    }
}