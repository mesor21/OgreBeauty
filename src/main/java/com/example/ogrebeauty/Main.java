package com.example.ogrebeauty;

import com.example.ogrebeauty.entity.Service;
import com.example.ogrebeauty.repository.PostgreSQLJDBC;
import com.example.ogrebeauty.service.ServiceService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setTitle("Ogre Beaity");
        //TODO set icon

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        PostgreSQLJDBC connectionDatabase = new PostgreSQLJDBC();
        connectionDatabase.connectTest();
        ServiceService service=new ServiceService();
        List<Service> list= service.find("lol","employeeFullname");
        System.out.println(list);
        /*connectionDatabase.autocreateTables();*/
        launch();
    }
}