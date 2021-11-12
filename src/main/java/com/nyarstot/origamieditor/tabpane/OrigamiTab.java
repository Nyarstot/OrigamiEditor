package com.nyarstot.origamieditor.tabpane;

import com.nyarstot.origamieditor.editor.OrigamiTextArea;
import javafx.scene.control.Tab;

import java.io.File;

public class OrigamiTab {

    private final OrigamiTextArea textArea;
    private final Tab thisTab;

    private boolean edited;

    public OrigamiTab(String name) {
        textArea = new OrigamiTextArea();
        thisTab = new Tab(name, textArea.getNode());

        edited = false;
    }

    public OrigamiTab(String name, File content) {
        textArea = new OrigamiTextArea();
        textArea.loadFile(content);
        thisTab = new Tab(name, textArea.getNode());

        edited = false;
    }

    public Tab getTab() { return thisTab; }

}
