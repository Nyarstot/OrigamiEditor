package com.nyarstot.origamieditor.textArea;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CodeHighlightDocumentParser {
    // Private
    private DocumentBuilder documentBuilder;
    private DocumentBuilderFactory documentBuilderFactory;
    private Document document;

    private String[] extensions;
    private String[] keywords;

    private CodeHighlightDocumentParser() {}

    private void parse() {
        Node root           = this.document.getDocumentElement();
        NodeList rootChild  = root.getChildNodes();

        for (int i = 0; i < rootChild.getLength(); i++) {

        }
    }
    // Public

    public CodeHighlightDocumentParser(DocumentBuilder documentBuilder,
                                       DocumentBuilderFactory documentBuilderFactory,
                                       Document document) {
        this.documentBuilder = documentBuilder;
        this.documentBuilderFactory = documentBuilderFactory;
        this.document = document;


    }

    public String[] getExtensions() {
        return this.extensions;
    }

    public String[] getHighlightKeywords() {
        return this.keywords;
    }
}
