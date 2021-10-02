package com.nyarstot.origamieditor.textArea;

import java.io.File;
import com.nyarstot.origamieditor.textArea.CodeHighlightDocumentParser;

/**
 * @author Kozlov Nikita
 */

public class CodeHighlightDocument {
    // Private

    private File        highlightDocument;
    private File        highlightStyle;

    private String[]    highlightExtensions;
    private String[]    highlightKeywords;

    private CodeHighlightDocumentParser parser = new CodeHighlightDocumentParser();

    // Public
    public CodeHighlightDocument() {

    }
    public CodeHighlightDocument(File file) {
        load(file);
    }

    public String[] getHighlightKeywords()      { return this.highlightKeywords; }
    public String[] getHighlightExtensions()    { return this.highlightExtensions; }
    public File getHighlightDocument()          { return this.highlightDocument; }
    public File getHighlightStyle()             { return this.highlightStyle; }

    public void load(File file) {
        this.highlightDocument = file;
        parser.parse(this.highlightDocument);
    }
}
