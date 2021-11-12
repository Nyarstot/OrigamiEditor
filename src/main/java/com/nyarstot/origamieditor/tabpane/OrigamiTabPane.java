package com.nyarstot.origamieditor.tabpane;

import com.nyarstot.origamieditor.MainWindow;
import com.nyarstot.origamieditor.editor.OrigamiTextArea;
import javafx.scene.Node;
import javafx.scene.control.TabPane;

import java.io.File;

public class OrigamiTabPane {

    private final TabPane tabPane;

    public OrigamiTabPane()
    {
        tabPane = new TabPane();

        tabPane.getTabs().add(new OrigamiTab("untitled").getTab());
        tabPane.getTabs().add(new OrigamiTab("untitled").getTab());
    }

    public void addNewTab(String name) {
        if (name != null) {
            tabPane.getTabs().add(new OrigamiTab(name).getTab());
        }
        //TODO: ALERT IF NULL
    }

    public void addNewTab(String name, File file) {
        if (name != null && file != null) {
            tabPane.getTabs().add(new OrigamiTab(name, file).getTab());
        }
        //TODO: ALERT IF NULL
    }

    public Node getNode() { return tabPane; }

}
