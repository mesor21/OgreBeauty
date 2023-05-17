package com.example.ogrebeauty.controller.helpClass;

import com.example.ogrebeauty.controller.helpClass.WindowManager;
import javafx.fxml.FXML;

import java.io.IOException;

public class RedirectController {
    protected WindowManager windowManager;
    @FXML
    protected void redirectToServicePage() throws IOException {
        windowManager.redirectToServicePage();
    }
    @FXML
    protected void redirectToEmployeesPage() throws IOException {
        windowManager.redirectToEmployeesPage();
    }
    @FXML
    protected void redirectToClientPage() throws IOException {
        windowManager.redirectToClientPage();
    }
    @FXML
    protected void redirectToServicesPage() throws IOException {
        windowManager.redirectToServicesPage();
    }

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }
}