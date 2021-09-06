package com.origamieditor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrigamiMainUiController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}