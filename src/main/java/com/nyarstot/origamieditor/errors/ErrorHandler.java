package com.nyarstot.origamieditor.errors;

import javafx.scene.control.Alert;

public class ErrorHandler {
    // Private

    private static Alert errorAlert = new Alert(Alert.AlertType.ERROR);

    // Public

    public void showError(String message) {
        errorAlert.setHeaderText("Error");
        errorAlert.setContentText(message);

        errorAlert.showAndWait();
    }
}
