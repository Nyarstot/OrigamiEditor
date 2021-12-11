package com.nyarstot.origamieditor;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
* @author Kozlov Nikita;
* */

public class OrigamiEditorApp extends Application {

    private MainWindow mainWindow;

    @Override
    public void start(Stage primaryStage) throws IOException {
        mainWindow = new MainWindow();

        primaryStage.setTitle("Origami Editor");
        primaryStage.setScene(mainWindow.getScene());
        primaryStage.show();
    }

    @Override
    public void stop() { mainWindow.getExecutor().shutdown(); }

    public static void main(String[] args) {
        try {
            launch(args);
        }
        catch (Exception error) {
            error.printStackTrace();
        }
        finally {
            System.exit(0);
        }
    }
}