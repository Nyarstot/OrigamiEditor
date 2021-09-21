package com.nyarstot.origamieditor.logic;

import java.nio.file.Path;
import java.util.List;

public class TextFile {
    // Private
    private Path filePath;
    private List<String> content;
    // Public
    public TextFile(Path filePath, List<String> content)
    {
        this.filePath = filePath;
        this.content = content;
    }

    public  TextFile(TextFile textFile)
    {
        this.filePath = textFile.getFilePath();
        this.content = textFile.getContent();
    }

    public TextFile()
    {
        this.filePath = null;
        this.content = null;
    }

    /* Class methods */

    public Path getFilePath()           { return this.filePath; }
    public List<String> getContent()    { return this.content; }

    public void clear()
    {
        this.filePath = null;
        if (this.content != null) {
            this.content.clear();
        }
    }
}
