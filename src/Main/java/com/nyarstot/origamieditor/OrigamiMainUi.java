package com.nyarstot.origamieditor;

import com.nyarstot.origamieditor.logic.TextFile;

import java.io.IOException;

import com.nyarstot.origamieditor.logic.EditorModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OrigamiMainUi extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OrigamiMainUi.class.getResource("OrigamiMain-view.fxml"));
        fxmlLoader.setControllerFactory(t -> new OrigamiMainUiController(new EditorModel()));
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