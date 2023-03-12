package com.example.demotest.core.errors.exceptions;

public final class ServerException extends Exception {
    private final String message;

    public String getMessage() {
        return message;
    }

    public ServerException(String message) {
        this.message = message;
    }
}
