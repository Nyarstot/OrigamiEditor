package com.nyarstot.origamieditor;

import com.nyarstot.origamieditor.logic.EditorModel;
import com.nyarstot.origamieditor.logic.IOResult;
import com.nyarstot.origamieditor.logic.TextFile;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Arrays;

public class OrigamiMainUiController {
    @FXML
    private TextArea textArea;
    private TextFile currentTextFile;
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
        TextFile textFile = new TextFile(currentTextFile.getFilePath(),
                Arrays.asList(textArea.getText().split("\n")));
        editorModel.save(textFile);
    }

    @FXML
    private void onClose() { editorModel.close(0); }

}