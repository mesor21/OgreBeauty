package com.example.ogrebeauty;

import com.example.ogrebeauty.controller.MainPageController;
import com.example.ogrebeauty.repository.PostgreSQLJDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainPage.fxml"));
        Pane pane = (Pane)fxmlLoader.load();
        MainPageController controller = (MainPageController) fxmlLoader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(pane, 1920, 1080);
        stage.setTitle("Ogre Beaity");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        PostgreSQLJDBC connectionDatabase = new PostgreSQLJDBC();
        connectionDatabase.connectTest();
        /*connectionDatabase.autocreateTables();*/
        launch();
    }
}