package com.nyarstot.origamieditor.tabpane;

import javafx.scene.Node;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import java.io.File;
import java.util.ArrayList;

public class OrigamiTabPane {

    private final TabPane tabPane;
    private int prevFindIndex;
    private ArrayList<OrigamiTab> tabArrayList;

    public OrigamiTabPane()
    {
        tabPane = new TabPane();
        tabArrayList = new ArrayList<>();
    }

    public void addNewTab(String name) {
        if (name != null) {
            OrigamiTab newTab = new OrigamiTab(name);

            tabArrayList.add(newTab);
            tabPane.getTabs().add(newTab.getTab());
        }
        //TODO: ALERT IF NULL
    }

    public void addNewTab(String name, File file) {
        if (name != null && file != null) {
            OrigamiTab newTab = new OrigamiTab(name, file);

            tabArrayList.add(newTab);
            tabPane.getTabs().add(newTab.getTab());
        }
        //TODO: ALERT IF NULL
    }

    public boolean isTabAlreadyExists(File file) {
        for (int i = 0; i < tabArrayList.size(); i++) {
            if (tabArrayList.get(i).getTextArea().getCurrentFilePath()
                    .equals(file.toString()))
            {
                prevFindIndex = i;
                return true;
            }
        }
        prevFindIndex = -1;
        return false;
    }

    public OrigamiTab getSelectedTab()
    {
        int index = this.tabPane.getSelectionModel().getSelectedIndex();
        return this.tabArrayList.get(index);
    }

    public void tabCallback() {
        setSelectedTab(prevFindIndex);
    }

    public void setSelectedTab(int tabIndex) {
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tabIndex);
    }

    public Node getNode() { return tabPane; }

}
