package com.nyarstot.origamieditor;

import com.nyarstot.origamieditor.codehighlighter.CodeHighlightDocument;
import com.nyarstot.origamieditor.editor.OrigamiFileControllerFactory;
import com.nyarstot.origamieditor.editor.TextFile;
import com.nyarstot.origamieditor.util.IOResult;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.io.File;
import java.util.Arrays;

/**
 * @author Kozlov Nikita;
 * */

public class MainWindowController {
    @FXML
    // Private
    private TextFile currentTextFile = new TextFile();
    private CodeHighlightDocument codeHighlightDocument = new CodeHighlightDocument();
    private final OrigamiFileControllerFactory editorModel;
    // Public
    public CodeArea codeArea;

    public MainWindowController(OrigamiFileControllerFactory editorModel) {
        this.editorModel = editorModel;
    }

    @FXML
    public void initialize()
    {
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        File file = new File("C:\\Users\\winte\\source\\Java\\OrigamiEditor\\src\\main\\resources\\com\\nyarstot\\origamieditor\\highlightings\\java.xml");
        codeHighlightDocument.load(file);
    }

    // File handlers

    @FXML
    private void onNew()
    {
        codeArea.clear();
        currentTextFile.clear();
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

                codeArea.clear();
                currentTextFile.getContent().forEach(line -> codeArea.appendText(line+"\n"));
            }
            else
            {
                System.out.println("Failed");
            }
        }
    }

    @FXML
    private void onSave()
    {
        if (currentTextFile.getFilePath() != null) {
            TextFile textFile = new TextFile(currentTextFile.getFilePath(),
                    Arrays.asList(codeArea.getText().split("\n")));
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
                    Arrays.asList(codeArea.getText().split("\n")));
            editorModel.save(textFile);

            currentTextFile = textFile;
        }
    }

    @FXML
    private void onClose() { editorModel.close(0); }
}