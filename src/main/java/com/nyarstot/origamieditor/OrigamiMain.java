package com.nyarstot.origamieditor;

import com.nyarstot.origamieditor.logic.TextFile;
import com.nyarstot.origamieditor.logic.EditorModel;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
* @author Kozlov Nikita;
* */

public class OrigamiMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OrigamiMain.class.getResource("OrigamiMain-view.fxml"));
        fxmlLoader.setControllerFactory(t -> new OrigamiMainController(new EditorModel()));
        TextFile curTextFile = new TextFile(null, null);

        Scene scene = new Scene(fxmlLoader.load(), 1089, 786);

        stage.setTitle("Origami Editor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}