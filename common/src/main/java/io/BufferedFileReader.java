package io;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class BufferedFileReader implements Closeable {
    private final String fileName;
    private final BufferedInputStream input;
    private final int BUFFER_SIZE = 100;
    private final int EOF_BYTE = -1;
    private final int NEWLINE_BYTE = 10;

    public BufferedFileReader(String fileName) throws IOException {
        FileInputStream inputStream = new FileInputStream(fileName);
        input = new BufferedInputStream(inputStream, BUFFER_SIZE);
        this.fileName = fileName;
    }

    public String getFilename() {
        return fileName;
    }

    public boolean hasNext() throws IOException {
        return input.available() > 0;
    }

    public String readLine() throws IOException {
        StringBuilder line = new StringBuilder();
        while (true) {
            int b = input.read();
            if (b == EOF_BYTE || b == NEWLINE_BYTE) break;
            line.append((char)b);
        }
        return line.toString().strip();
    }

    public String readAll() throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        buf.writeBytes(input.readAllBytes());
        return buf.toString(StandardCharsets.UTF_8);
    }

    public void close() throws IOException {
        input.close();
    }
}
