package com.nyarstot.origamieditor.editor;

import com.nyarstot.origamieditor.MainWindow;
import com.nyarstot.origamieditor.util.IOResult;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.io.File;
import java.util.Arrays;

public class OrigamiTextArea {
    // Private
    private TextFile currentTextFile;

    private final CodeArea codeArea;
    private final OrigamiFileControllerFactory controllerFactory;

    // Public
    public OrigamiTextArea() {
        currentTextFile = new TextFile();
        controllerFactory = new OrigamiFileControllerFactory();

        codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
    }

    public void newFile() {
        this.codeArea.clear();
        this.currentTextFile.clear();
    }

    public void loadFile(File file) {
        /*FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open file");
        fileChooser.setInitialDirectory(new File("./"));
        File file = fileChooser.showOpenDialog(null);*/

        if (file != null) {
            IOResult<TextFile> ioResult = controllerFactory.load(file.toPath());
            if (ioResult.s_ok() && ioResult.hasData()) {
                currentTextFile = ioResult.getData();

                this.codeArea.clear();
                this.currentTextFile.getContent().forEach(line -> this.codeArea.appendText(line+"\n"));
            }
            else
            {
                System.out.println("Failed to load file");
            }
        }
    }

    public void saveFile() {
        if (this.currentTextFile.getFilePath() != null) {
            TextFile textFile = new TextFile(this.currentTextFile.getFilePath(),
                    Arrays.asList(this.codeArea.getText().split("\n")));
            this.controllerFactory.save(textFile);

            this.currentTextFile = textFile;
        } else { saveFileAs(); }
    }

    public void saveFileAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as");
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            TextFile textFile = new TextFile(file.toPath(),
                    Arrays.asList(codeArea.getText().split("\n")));
            this.controllerFactory.save(textFile);

            this.currentTextFile = textFile;
        }
    }

    public String getText() {
        return this.codeArea.getText();
    }

    public Node getNode() { return (new VirtualizedScrollPane<>(this.codeArea)); }
    public CodeArea getCodeArea() { return this.codeArea; };
}
