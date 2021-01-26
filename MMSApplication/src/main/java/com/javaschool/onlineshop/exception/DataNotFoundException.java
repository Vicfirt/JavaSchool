package com.javaschool.onlineshop.exception;

/**
 * This exception is thrown when the requested information is missing.
 */
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }
}
