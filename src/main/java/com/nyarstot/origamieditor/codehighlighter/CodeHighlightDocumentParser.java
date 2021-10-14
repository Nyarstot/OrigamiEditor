package com.nyarstot.origamieditor.codehighlighter;

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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Pattern;

public class CodeHighlightDocumentParser {
    // private
    DocumentBuilder documentBuilder                 = null;
    DocumentBuilderFactory documentBuilderFactory   = null;
    Document document                               = null;

    private String[]    extensions;
    private String[]    keywords;
    private Path        style;

    private String keywordPattern;
    private String parenPattern;
    private String bracePattern;
    private String bracketPattern;
    private String semicolonPattern;
    private String stringPattern;
    private String commentPattern;

    private void xmlArrayDataToFileMeta (String nodeName, Node node) {
        if (node.getNodeName().equals(nodeName)) {
            NodeList nodeList = node.getChildNodes();
            String[] stringArr = new String[nodeList.getLength()/2];
            int tmpCounter = 1;
            for (int i = 1; i < nodeList.getLength(); i++) {
                Node currNode = nodeList.item(i);
                if (currNode.getNodeType() == Node.ELEMENT_NODE) {
                    stringArr[i - tmpCounter] = currNode.getTextContent();
                    tmpCounter++;
                }
            }
            if (nodeName.equals("keywords")) {this.keywords = stringArr; };
            if (nodeName.equals("fileExtensions")) {this.extensions = stringArr; };
        }
    };
    private void parseHighlightRules(Node node) {
        if (Objects.equals(node.getNodeName(), "highlightPatterns")) {
            Element element = (Element) node;
            this.parenPattern = element.getElementsByTagName("parenPattern").item(0).getTextContent();
            this.bracePattern = element.getElementsByTagName("bracePattern").item(0).getTextContent();
            this.bracketPattern = element.getElementsByTagName("bracketPattern").item(0).getTextContent();
            this.semicolonPattern = element.getElementsByTagName("semicolonPattern").item(0).getTextContent();
            this.stringPattern = element.getElementsByTagName("stringPattern").item(0).getTextContent();
            this.commentPattern = element.getElementsByTagName("commentPattern").item(0).getTextContent();
        }
    }

    // public
    CodeHighlightDocumentParser() {
        try {
            documentBuilderFactory  = DocumentBuilderFactory.newInstance();
            documentBuilder         = documentBuilderFactory.newDocumentBuilder();

            this.extensions = new String[0];
            this.keywords   = new String[0];
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public String[] getKeywords()       { return this.keywords; }
    public String[] getExtensions()     { return this.extensions; }
    public Path getStyle()              { return this.style; }
    public String getParenPattern()     { return this.parenPattern; }
    public String getBracePattern()     { return this.bracePattern; }
    public String getBracketPattern()   { return this.bracketPattern; }
    public String getSemicolonPattern() { return this.semicolonPattern; }
    public String getStringPattern()    { return this.stringPattern; }
    public String getCommentPattern()   { return this.commentPattern; }

    public Pattern getHighlightPattern() {
        return Pattern.compile(
                "(?<KEYWORD>" + this.keywordPattern + ")"
                        + "|(?<PAREN>" + this.parenPattern + ")"
                        + "|(?<BRACE>" + this.bracePattern + ")"
                        + "|(?<BRACKET>" + this.bracketPattern + ")"
                        + "|(?<SEMICOLON>" + this.semicolonPattern + ")"
                        + "|(?<STRING>" + this.stringPattern + ")"
                        + "|(?<COMMENT>" + this.commentPattern + ")"
        );
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
                    xmlArrayDataToFileMeta("fileExtensions", tmpItemNode);
                    xmlArrayDataToFileMeta("keywords", tmpItemNode);
                    parseHighlightRules(tmpItemNode);
                    if (tmpItemNode.getNodeName().equals("style")) {
                        NodeList styleNode = tmpItemNode.getChildNodes();
                        this.style = Paths.get(styleNode.item(1).getTextContent());
                    }
                }
            }

            this.keywordPattern = "\\b(" + String.join("|", this.keywords) + ")\\b";
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
