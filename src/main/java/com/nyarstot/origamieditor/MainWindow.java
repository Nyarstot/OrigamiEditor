package com.nyarstot.origamieditor;

import com.nyarstot.origamieditor.codehighlighter.CodeHighlightDocument;
import com.nyarstot.origamieditor.codehighlighter.CodeHighlighterAsync;
import com.nyarstot.origamieditor.folderview.OrigamiFolderView;
import com.nyarstot.origamieditor.markdown.MarkdownWindow;
import com.nyarstot.origamieditor.tabpane.OrigamiTabPane;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainWindow {
    // Private

    private final Scene scene;
    private final OrigamiTabPane tabPane;
    private final OrigamiFolderView folderView;
    private final MenuBar menuBar;
    private final ToolBar toolBar;
    private final ExecutorService executor;
    private final SplitPane splitPane;

    private final CodeHighlightDocument codeHighlightDocument;
    private CodeHighlighterAsync codeHighlighter;

    // Public

    MainWindow() {
        executor = Executors.newSingleThreadExecutor();
        codeHighlightDocument =
                new CodeHighlightDocument(new File("C:\\Users\\winte\\source\\Java\\OrigamiEditor\\src\\main\\resources\\com\\nyarstot\\origamieditor\\highlighting\\java.xml"));

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

        tabPane = new OrigamiTabPane();

        folderView = new OrigamiFolderView(this);

        splitPane = new SplitPane();
        splitPane.getItems().add(folderView.getNode());
        splitPane.getItems().add(tabPane.getNode());

        splitPane.setDividerPositions(0.25);

        borderPane.setCenter(splitPane);

        // Border pane bottom

        ToolBar bottomToolBar = new ToolBar();
        borderPane.setBottom(bottomToolBar);

        scene = new Scene(borderPane);
        //scene.getStylesheets().add(OrigamiEditorApp.class.getResource("editor/OrigamiDarkTheme.css").toExternalForm());
    }

    private void setMenuBarAndToolBarButtons() {
        /* Set menu bar buttons */
        Menu fileMenu       = new Menu("File");
        MenuItem newItem    = new MenuItem("New");
        newItem.setOnAction(e -> {
            this.tabPane.addNewTab("untitled");
        });
        MenuItem openItem = new MenuItem("Open");
        openItem.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();

            fileChooser.setTitle("Open file");
            fileChooser.setInitialDirectory(new File("./"));
            File file = fileChooser.showOpenDialog(null);

            if (!tabPane.isTabAlreadyExists(file)) {
                tabPane.addNewTab(file.getName().toString(), file);
                folderView.loadDirectory(new File(file.getParent()));
            } else {
                tabPane.tabCallback();
            }
        });
        MenuItem openFolderItem = new MenuItem("Open folder");
        openFolderItem.setOnAction(e -> {
            this.folderView.openFolder();
        });
        MenuItem saveItem = new MenuItem("Save");
        saveItem.setOnAction(e -> {
            tabPane.getSelectedTab().getTextArea().saveFile();
        });
        MenuItem saveAsItem = new MenuItem("Save as");
        saveAsItem.setOnAction(e -> {
            tabPane.getSelectedTab().getTextArea().saveFileAs();
        });
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> {
            System.exit(0);
            this.executor.shutdown();
        });
        fileMenu.getItems().addAll(
                newItem,
                openItem,
                openFolderItem,
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
        itemGit.setOnAction(event -> {
            modeMenuButton.setText("Git README");
            if (splitPane.getItems().size() == 2) {
                //MarkdownWindow markdownWindow = new MarkdownWindow();
                //this.splitPane.getItems().add(markdownWindow.getNode());
            }
        });
        MenuItem itemCode   = new MenuItem("Code writing");
        itemCode.setOnAction(event -> {
            modeMenuButton.setText("Code writing");
            if (splitPane.getItems().size() > 2) {
                this.splitPane.getItems().remove(2);
            }

            codeHighlighter = new CodeHighlighterAsync(tabPane.getSelectedTab().getTextArea(), executor);
            codeHighlighter.loadHighlightPattern(codeHighlightDocument.getHighlightPattern());
            codeHighlighter.cleanUpWhenDone(tabPane.getSelectedTab().getTextArea());

            scene.getStylesheets().add(OrigamiEditorApp.class.getResource("highlighting/java.css").toExternalForm());
        });
        modeMenuButton.getItems().addAll(
                itemGit,
                itemCode);

        this.toolBar.getItems().addAll(modeMenuButton);

    }

    Scene getScene() {
        return this.scene;
    }

    ExecutorService getExecutor() {
        return this.executor;
    }
}
