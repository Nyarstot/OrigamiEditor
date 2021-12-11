package com.nyarstot.origamieditor.markdown;

import java.util.Scanner;

public class MarkdownParser {

    public String parse(String text){
        String header = "<html>\n<body>\n";
        String footer = "</body>\n</html>\n";

        Scanner scanner = new Scanner(text);
        String modifiedText = "";

        // First pass
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("#"))
            {
                line = line.substring(2);
                modifiedText += "<h1>" + line + "</h1> \n";
            }
            else if (line.startsWith("##"))
            {
                line = line.substring(3);
                modifiedText += "<h2>" + line + "</h2> \n";
            }
            else if (line.startsWith("###"))
            {
                line = line.substring(4);
                modifiedText += "<h3>" + line + "</h3> \n";
            }
            else if (line.startsWith("---"))
            {
                modifiedText += "<hr /> \n";
            }
            else
            {
                modifiedText += line + "<br /> \n";
            }
        }

        // Second pass
        String[] symbols = {"**", "``", "__", "~~"};
        String[] tagNames = {"b", "i", "u", "s"};
        for (int i = 0; i < symbols.length; i++) {
            String symbol = symbols[i];
            String tagName = tagNames[i];
            boolean tagStart = true;

            while (modifiedText.contains(symbol)) {
                int index = modifiedText.indexOf(symbol);

                String beforeSymbol = modifiedText.substring(0, index);
                String afterSymbol = modifiedText.substring(index+2);

                String tag;
                if (tagStart) {
                    tag = "<" + tagName + ">";
                }
                else
                {
                    tag = "</" + tagName + ">";
                }

                modifiedText = beforeSymbol + tag + afterSymbol;

                tagStart = !tagStart;
            }
        }

        return (header + modifiedText + footer);
    }
}