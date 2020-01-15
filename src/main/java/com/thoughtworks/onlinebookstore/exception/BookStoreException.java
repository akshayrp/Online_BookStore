package com.thoughtworks.onlinebookstore.exception;

public class BookStoreException extends Exception {

    private ExceptionType type;

    public BookStoreException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public ExceptionType getType() {
        return type;
    }

    public enum ExceptionType {
        CANT_ADD_NULL_DATA;
    }
}
