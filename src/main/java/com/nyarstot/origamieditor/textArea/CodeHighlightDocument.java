package com.nyarstot.origamieditor.textArea;

import java.io.File;
import java.nio.file.Path;

/**
 * @author Kozlov Nikita
 */

public class CodeHighlightDocument {
    // Private

    private File        highlightDocument;
    private Path        highlightStyle;

    private String[]    highlightExtensions;
    private String[]    highlightKeywords;

    private String keywordPattern;
    private String parenPattern;
    private String bracePattern;
    private String bracketPattern;
    private String semicolonPattern;
    private String stringPattern;
    private String commentPattern;

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

    public void load(File file) {
        this.highlightDocument = file;
        parser.parse(this.highlightDocument);

        this.highlightExtensions    = parser.getExtensions();
        this.highlightKeywords      = parser.getKeywords();
        this.highlightStyle         = parser.getStyle();

        this.keywordPattern         = "\\b(" + String.join("|", this.highlightKeywords) + ")\\b";

        this.parenPattern           = parser.getParenPattern();
        this.bracePattern           = parser.getBracePattern();
        this.bracketPattern         = parser.getBracketPattern();
        this.semicolonPattern       = parser.getSemicolonPattern();
        this.stringPattern          = parser.getStringPattern();
        this.commentPattern         = parser.getCommentPattern();
    }
}
