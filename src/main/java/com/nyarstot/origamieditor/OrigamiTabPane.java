package com.nyarstot.origamieditor;


import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.Node;
import javafx.scene.control.TabPane;

public class OrigamiTabPane {
    // Private

    private final MainWindow mainWindow;
    private final TabPane tabPane;

    // Public

    OrigamiTabPane(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        tabPane = new TabPane();
        tabPane.setFocusTraversable(false);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
    }

    Node getNode() { return tabPane; }
}
