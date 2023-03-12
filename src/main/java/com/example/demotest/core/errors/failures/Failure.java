package com.example.demotest.core.errors.failures;

public abstract class Failure {
    private final String message;

    public final String getMessage() {
        return message;
    }

    protected Failure(String message) {
        this.message = message;
    }
}
