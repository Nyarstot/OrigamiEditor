package com.nyarstot.origamieditor;

import com.nyarstot.origamieditor.codehighlighter.CodeHighlightDocument;
import com.nyarstot.origamieditor.codehighlighter.CodeHighlighterAsync;
import com.nyarstot.origamieditor.editor.OrigamiTextArea;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.reactfx.Subscription;

import java.io.File;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.prefs.Preferences;

public class MainWindow {
    // Private

    private final Scene scene;
    private final OrigamiTextArea textArea;
    private final MenuBar menuBar;
    private final ToolBar toolBar;
    private final ExecutorService executor;

    private CodeHighlightDocument codeHighlightDocument;
    private CodeHighlighterAsync codeHighlighter;

    // Public

    MainWindow() {
        executor = Executors.newSingleThreadExecutor();
        codeHighlightDocument =
                new CodeHighlightDocument(new File("C:\\Users\\winte\\source\\Java\\OrigamiEditor\\src\\main\\resources\\com\\nyarstot\\origamieditor\\highlightings\\java.xml"));

        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(800, 600);

        // Border pane top

        VBox vBox = new VBox();

        menuBar = new MenuBar();
        toolBar = new ToolBar();
        setMenuBarAndToolBarButtons();
        vBox.getChildren().addAll(menuBar, toolBar);
        borderPane.setTop(vBox);

        // Border pane center

        textArea = new OrigamiTextArea(this);
        codeHighlighter = new CodeHighlighterAsync(textArea, executor);
        codeHighlighter.loadHighlightPattern(codeHighlightDocument.getHighlightPattern());
        codeHighlighter.cleanUpWhenDone(textArea);

        SplitPane splitPane = new SplitPane();
        splitPane.getItems().add(new AnchorPane());
        splitPane.getItems().add(textArea.getNode());

        borderPane.setCenter(splitPane);

        // Border pane bottom

        ToolBar bottomToolBar = new ToolBar();
        borderPane.setBottom(bottomToolBar);

        scene = new Scene(borderPane);
        //scene.getStylesheets().add("com/nyarstot/origamieditor/editor/OrigamiDark.css");
        scene.getStylesheets().add(OrigamiEditorApp.class.getResource("highlightings/java.css").toExternalForm());
    }

    private void setMenuBarAndToolBarButtons() {
        /* Set menu bar buttons */
        Menu fileMenu       = new Menu("File");
        MenuItem newItem    = new MenuItem("New");
        newItem.setOnAction(e -> {
            this.textArea.newFile();
        });
        MenuItem openItem   = new MenuItem("Open");
        openItem.setOnAction(e -> {
            this.textArea.loadFile();
        });
        MenuItem saveItem   = new MenuItem("Save");
        saveItem.setOnAction(e -> {
            this.textArea.saveFile();
        });
        MenuItem saveAsItem = new MenuItem("Save as");
        saveAsItem.setOnAction(e -> {
            this.textArea.saveFileAs();
        });
        MenuItem exitItem   = new MenuItem("Exit");
        exitItem.setOnAction(e -> {
            System.exit(0);
        });
        fileMenu.getItems().addAll(
                newItem,
                openItem,
                saveItem,
                saveAsItem,
                exitItem);

        Menu editMenu       = new Menu("Edit");
        MenuItem undoItem   = new MenuItem("Undo");
        MenuItem redoItem   = new MenuItem("Redo");
        editMenu.getItems().addAll(
                undoItem,
                redoItem);

        Menu viewMenu       = new Menu("Code");
        Menu selectionMenu  = new Menu("Selection");
        Menu findMenu       = new Menu("Find");
        Menu packageMenu    = new Menu("Package");
        Menu helpMenu       = new Menu("Help");

        this.menuBar.getMenus().addAll(
                fileMenu,
                editMenu,
                viewMenu,
                selectionMenu,
                findMenu,
                packageMenu,
                helpMenu);

        /* Set toolbar buttons */

        MenuButton modeMenuButton = new MenuButton("Work mode");
        MenuItem itemGit    = new MenuItem("Git README");
        MenuItem itemCode   = new MenuItem("Code writing");
        modeMenuButton.getItems().addAll(
                itemGit,
                itemCode);

        this.toolBar.getItems().addAll(modeMenuButton);

    }

    Scene getScene() {
        return this.scene;
    }
}