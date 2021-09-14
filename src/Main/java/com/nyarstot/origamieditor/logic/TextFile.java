package com.nyarstot.origamieditor.logic;

import java.nio.file.Path;
import java.util.List;

public class TextFile {
    // Private
    private final Path filePath;
    private final List<String> content;
    // Public
    public TextFile(Path filePath, List<String> content)
    {
        this.filePath = filePath;
        this.content = content;
    }

    public Path getFilePath()           { return this.filePath; }
    public List<String> getContent()    { return this.content; }
}
