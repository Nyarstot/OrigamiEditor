package com.nyarstot.origamieditor;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import com.nyarstot.origamieditor.logic.EditorModel;
import com.nyarstot.origamieditor.logic.IOResult;
import com.nyarstot.origamieditor.logic.TextFile;

import java.io.File;
import java.util.Arrays;

public class OrigamiMainUiController {
    @FXML
    private TextArea textArea;
    private TextFile currentTextFile = new TextFile();
    private final EditorModel editorModel;

    public OrigamiMainUiController(EditorModel editorModel) {
        this.editorModel = editorModel;
    }

    @FXML
    private void onLoad()
    {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open file");
        fileChooser.setInitialDirectory(new File("./"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            IOResult<TextFile> ioResult = editorModel.load(file.toPath());
            if (ioResult.s_ok() && ioResult.hasData()) {
                currentTextFile = ioResult.getData();

                textArea.clear();
                currentTextFile.getContent().forEach(line -> textArea.appendText(line + "\n"));
            }
            else {
                System.out.println("Failed");
            }
        }
    }

    @FXML
    private void onSave()
    {
        if (currentTextFile.getFilePath() != null) {
            TextFile textFile = new TextFile(currentTextFile.getFilePath(),
                    Arrays.asList(textArea.getText().split("\n")));
            editorModel.save(textFile);

            currentTextFile = textFile;
        } else { onSaveAs(); }
    }

    @FXML
    private void onSaveAs()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as");
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            TextFile textFile = new TextFile(file.toPath(),
                    Arrays.asList(textArea.getText().split("\n")));
            editorModel.save(textFile);

            currentTextFile = textFile;
        }
    }

    @FXML
    private void onClose() { editorModel.close(0); }

    @FXML
    private void onNew()
    {
        textArea.clear();
        currentTextFile.clear();
    }
}