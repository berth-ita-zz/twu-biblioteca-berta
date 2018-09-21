package com.twu.biblioteca.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader  implements AutoCloseable {

    private final BufferedReader bufferedReader;

    public FileReader(String fileName) throws IOException {
        this.bufferedReader = Files.newBufferedReader(Paths.get(fileName));
    }

    public String readLine() throws Exception {
        return bufferedReader.readLine();
    }

    @Override
    public void close() throws Exception {
        if (bufferedReader != null) {
            bufferedReader.close();
        }
    }
}

