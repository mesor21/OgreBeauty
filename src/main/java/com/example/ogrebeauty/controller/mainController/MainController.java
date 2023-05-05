package com.example.ogrebeauty.controller.mainController;

import com.example.ogrebeauty.Main;
import com.example.ogrebeauty.service.MainService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Date;

public class MainController extends RedirectController {
    private Date selectedDate;
    private MainService mainService;

    @FXML
    private Button firstDate;
    @FXML
    private Button secondDate;
    @FXML
    private Button thirdDate;
    @FXML
    private Button fourthDate;
    @FXML
    private Button fifthDate;
    @FXML
    private Scene scene;
    @FXML
    private Label summByDay;

    public MainController() {
        this.mainService= new MainService();
        this.selectedDate = new Date();
    }
    public void initialize(){
        //summByDay.setText("Выручка за день: "+mainService.summPearDay(selectedDate)+"");
        firstDate.setText((selectedDate.getDate()-2)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        secondDate.setText((selectedDate.getDate()-1)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        thirdDate.setText(selectedDate.getDate()+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        fourthDate.setText((selectedDate.getDate()+1)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        fifthDate.setText((selectedDate.getDate()+2)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
    }
    private void open(){
        firstDate.setText((selectedDate.getDate()-2)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        secondDate.setText((selectedDate.getDate()-1)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        thirdDate.setText(selectedDate.getDate()+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        fourthDate.setText((selectedDate.getDate()+1)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));
        fifthDate.setText((selectedDate.getDate()+2)+"."+(selectedDate.getMonth()+1)+"."+(selectedDate.getYear()+1900));

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainPage/mainPage.fxml"));
        try {
            Pane pane = (Pane)fxmlLoader.load();
            scene=new Scene(pane, 1920,1080);
            openStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        openStage.setTitle("Ogre Beaity");
        openStage.show();
    }
    public void onClickFirstDate(){
        this.selectedDate.setDate(selectedDate.getDay()-2);
        open();
    }
    public void onClickSecondDate(){
        this.selectedDate.setDate(selectedDate.getDay()-1);
        open();
    }
    public void onClickFourthDate(){
        this.selectedDate.setDate(selectedDate.getDay()+1);
        open();
    }
    public void onClickFifthDate(){
        this.selectedDate.setDate(selectedDate.getDay()+2);
        open();
    }
    public void setSummByDay(String summ) {
        Label summByDay = new Label("Выручка за день: "+summ);
        this.summByDay = summByDay;
    }
}
