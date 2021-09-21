package com.nyarstot.origamieditor.textArea;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Kozlov Nikita
 */

public class CodeHighlightDocument {
    // Private

    private File        highlightDocument;
    private File        highlightStyle;

    private String[]    highlightExtensions;
    private String[]    highlightKeywords;

    // Public

    public void load(File file) {
        DocumentBuilder         documentBuilder;
        DocumentBuilderFactory  documentBuilderFactory;
        Document                document;

        try {
            documentBuilderFactory  = DocumentBuilderFactory.newInstance();
            documentBuilder         = documentBuilderFactory.newDocumentBuilder();
            document                = documentBuilder.parse(file);

            NodeList nodeList = document.getElementsByTagName("keywords");


        } catch (ParserConfigurationException
                | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
