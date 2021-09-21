package com.nyarstot.origamieditor.textArea;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class TextHighlighter {
    // Private

    private File xmlFile;

    // Public

    public void loadHighlightKeywordsFile(File file){
        DocumentBuilder         documentBuilder;
        DocumentBuilderFactory  documentBuilderFactory;
        Document                document;

        try {
            documentBuilderFactory  = DocumentBuilderFactory.newInstance();
            documentBuilder         = documentBuilderFactory.newDocumentBuilder();
            document                = documentBuilder.parse(file);

            Node root               = document.getDocumentElement();
            NodeList params = root.getChildNodes();

            for (int i = 0; i < params.getLength(); i++) {
                Node item = params.item(i);
                if (item.getNodeType() != Node.TEXT_NODE) {
                    NamedNodeMap attributes = item.getAttributes();
                    Node nameAttribute      = attributes.getNamedItem("highlight");
                    System.out.println(i + "." + nameAttribute.getNodeValue());
                }
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }

    }
}
