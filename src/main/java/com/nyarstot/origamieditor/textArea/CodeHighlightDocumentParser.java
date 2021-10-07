package com.nyarstot.origamieditor.textArea;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class CodeHighlightDocumentParser {
    // private

    DocumentBuilder documentBuilder                 = null;
    DocumentBuilderFactory documentBuilderFactory   = null;
    Document document                               = null;

    private String[]    extensions;
    private String[]    keywords;
    private File        style;

    private void xmlStringDataToStringArray (String[] stringArr, String nodeName, Node node) {

    };

    // public
    CodeHighlightDocumentParser() {
        try {
            documentBuilderFactory  = DocumentBuilderFactory.newInstance();
            documentBuilder         = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void parse(File xmlFile) {
        try {
            if (documentBuilderFactory == null) {
                documentBuilderFactory  = DocumentBuilderFactory.newInstance();
                documentBuilder         = documentBuilderFactory.newDocumentBuilder();
            }

            document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList root = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < root.getLength(); i++) {
                Node tmpItemNode = root.item(i);
                if (tmpItemNode.getNodeType() == Node.ELEMENT_NODE) {

                    if (tmpItemNode.getNodeName().equals("keywords")) {
                        NodeList keys = tmpItemNode.getChildNodes();
                        int tmpCounter = 1;
                        keywords = new String[keys.getLength()/2];
                        for (int j = 1; j < keys.getLength(); j+=2) {
                            Node key = keys.item(j);
                            if (key.getNodeType() == Node.ELEMENT_NODE) {
                                keywords[j - tmpCounter] = key.getTextContent();
                                tmpCounter++;
                            }
                        }
                    }

                }
            }

            for (int i = 0; i < keywords.length; i++) {
                System.out.println(keywords[i]);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

}
