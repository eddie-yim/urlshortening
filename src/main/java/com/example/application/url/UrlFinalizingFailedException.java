package com.example.application.url;

public class UrlFinalizingFailedException extends RuntimeException {
    public UrlFinalizingFailedException() {
    }

    public UrlFinalizingFailedException(Throwable cause) {
        super(cause);
    }

    public UrlFinalizingFailedException(String message) {
        super(message);
    }
}
