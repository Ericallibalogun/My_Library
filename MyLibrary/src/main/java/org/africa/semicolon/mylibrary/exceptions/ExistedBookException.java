package org.africa.semicolon.mylibrary.exceptions;

public class ExistedBookException extends RuntimeException {
    public ExistedBookException(String message) {
        super(message);
    }
}
