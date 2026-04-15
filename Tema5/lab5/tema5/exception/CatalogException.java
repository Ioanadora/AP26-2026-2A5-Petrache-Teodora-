package com.lab5.tema5.exception;
public class CatalogException extends Exception {
    public CatalogException(String message) {
        super(message);
    }

    public CatalogException(String message, Throwable cause) {
        super(message, cause);
    }
}