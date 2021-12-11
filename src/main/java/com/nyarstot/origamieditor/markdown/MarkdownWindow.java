package com.nyarstot.origamieditor.markdown;

import com.nyarstot.origamieditor.editor.OrigamiTextArea;
import javafx.scene.Node;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MarkdownWindow {
    private WebEngine webEngine;
    private WebView webView;

    private final MarkdownParser markdownParser;

    public MarkdownWindow() {
        webView = new WebView();
        webEngine = new WebEngine();
        markdownParser = new MarkdownParser();

        webEngine = webView.getEngine();
    }

    public void loadContent(OrigamiTextArea textArea) {
        webEngine.loadContent(markdownParser.parse(textArea.getText()));
    }

    public Node getNode() { return webView; }
}
