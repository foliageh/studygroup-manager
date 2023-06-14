package io.exceptions;

import java.io.IOException;

public class FileAccessException extends IOException {
    public FileAccessException(String errorMessage) {
        super(errorMessage);
    }
}
