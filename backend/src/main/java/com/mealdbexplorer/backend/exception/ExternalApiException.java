package com.mealdbexplorer.backend.exception;

public class ExternalApiException
        extends RuntimeException {

    public ExternalApiException(String message) {
        super(message);
    }
}