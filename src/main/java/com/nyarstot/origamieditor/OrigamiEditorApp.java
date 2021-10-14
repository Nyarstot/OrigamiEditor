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
        /*FXMLLoader fxmlLoader = new FXMLLoader(OrigamiEditorApp.class.getResource("OrigamiMain-view.fxml"));
        fxmlLoader.setControllerFactory(t -> new MainWindowController(new EditorModel()));
        Scene scene = new Scene(fxmlLoader.load(), 1089, 786);

        stage.setTitle("Origami Editor");
        stage.setScene(scene);
        stage.show();*/

        mainWindow = new MainWindow();

        primaryStage.setTitle("Origami Editor");
        primaryStage.setScene(mainWindow.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}