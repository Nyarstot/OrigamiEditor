package com.nyarstot.origamieditor.folderview;

import com.nyarstot.origamieditor.MainWindow;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;
import java.util.Objects;

public class OrigamiFolderView {
    // Private

    private TreeView<String> treeView;
    private File currentFolder;

    private TreeItem<String> getNodesForDirectory(File dir) {
        TreeItem<String> root = new TreeItem<String>(dir.getName());

        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) { // Then call function recursively
                root.getChildren().add(getNodesForDirectory(file));
            } else {
                root.getChildren().add(new TreeItem<String>(file.getName()));
            }
        }

        return root;
    }

    // Public

    public OrigamiFolderView(MainWindow mainWindow) {
        treeView = new TreeView<String>();
    }

    public void loadDirectory(File dir) {
        if (dir != null) {
            this.currentFolder = dir;
            this.treeView.setRoot(getNodesForDirectory(dir));
        } else {

        }
    }

    public Node getNode() { return this.treeView; }
}
