package com.nyarstot.origamieditor.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class EditorModel {
    // Private

    // Public
    public IOResult<TextFile> load(Path file) {
        try {
            List<String> lines = Files.readAllLines(file);
            return new IOResult<TextFile>(true, new TextFile(file, lines));
        } catch (IOException e) {
            e.printStackTrace();
            return new IOResult<>(false, null);
        }
    }

    public void save(TextFile file) {
        try {
            Files.write(file.getFilePath(), file.getContent(), StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(int status) {
        System.exit(status);
    }
}
