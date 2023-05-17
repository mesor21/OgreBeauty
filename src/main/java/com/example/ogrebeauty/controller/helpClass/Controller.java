package com.example.ogrebeauty.controller.helpClass;

import com.example.ogrebeauty.controller.DTO.ServiceDTO;
import javafx.collections.ObservableList;

public interface Controller<Data> {
    void initialize();
    void setWindowManager(WindowManager windowManager);
}
