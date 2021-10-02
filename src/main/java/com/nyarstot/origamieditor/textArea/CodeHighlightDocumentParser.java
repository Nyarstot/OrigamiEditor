package com.nyarstot.origamieditor.textArea;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.nyarstot.origamieditor.textArea.CodeHighlightDocument;

public class CodeHighlightDocumentParser {
    // Private

    private DocumentBuilderFactory documentBuilderFactory = null;
    private DocumentBuilder documentBuilder               = null;
    private Document document                             = null;

    private String[]    extensions;
    private String[]    keywords;
    private File        style;

    // Public

    public CodeHighlightDocumentParser() {
        try {
            this.documentBuilderFactory = DocumentBuilderFactory.newInstance();
            this.documentBuilder = this.documentBuilderFactory.newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void parse(File xmlFile) {
        try {
            this.document = this.documentBuilder.parse(xmlFile);
            this.document.getDocumentElement().normalize();

            Element root = this.document.getDocumentElement();
            System.out.println(root.getNodeName());

            NodeList highlightAttributes = root.getChildNodes();
            for (int i = 0; i < highlightAttributes.getLength(); i++) {
                Node currNode = highlightAttributes.item(i);
                if (currNode.getNodeType() == Node.ELEMENT_NODE) {

                    if (Objects.equals(currNode.getNodeName(), "keywords")) {
                        NodeList keys = currNode.getChildNodes();
                        this.keywords = new String[keys.getLength()];
                        for (int j = 0; j < keys.getLength(); j++) {
                            Node key = keys.item(j);
                            if (key.getNodeType() == Node.ELEMENT_NODE) {
                                this.keywords[j-1] =   key.getTextContent();
                            }
                        }
                    }

                }
            }
            for (int i = 0; i < keywords.length; i++) {
                System.out.println(keywords[i]);
            }
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
