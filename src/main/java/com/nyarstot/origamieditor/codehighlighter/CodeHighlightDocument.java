package com.nyarstot.origamieditor.codehighlighter;

import java.io.File;
import java.nio.file.Path;
import java.util.regex.Pattern;

/**
 * @author Kozlov Nikita
 */

public class CodeHighlightDocument {
    // Private

    private File        highlightDocument;
    private Path        highlightStyle;

    private String[]    highlightExtensions;
    private String[]    highlightKeywords;

    private Pattern highlightPattern;

    private final CodeHighlightDocumentParser parser = new CodeHighlightDocumentParser();

    // Public
    public CodeHighlightDocument() {

    }
    public CodeHighlightDocument(File file) {
        load(file);
    }

    public String[] getHighlightKeywords()      { return this.highlightKeywords; }
    public String[] getHighlightExtensions()    { return this.highlightExtensions; }
    public File getHighlightDocument()          { return this.highlightDocument; }
    public Path getHighlightStyle()             { return this.highlightStyle; }
    public Pattern getHighlightPattern()        { return this.highlightPattern; }

    public void load(File file) {
        this.highlightDocument = file;
        parser.parse(this.highlightDocument);

        this.highlightExtensions    = parser.getExtensions();
        this.highlightKeywords      = parser.getKeywords();
        this.highlightStyle         = parser.getStyle();

        this.highlightPattern       = parser.getHighlightPattern();
    }
}
