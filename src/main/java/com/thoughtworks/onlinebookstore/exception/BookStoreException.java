package com.thoughtworks.onlinebookstore.exception;

public class BookStoreException extends Exception {
    private ExceptionType type;

    public enum ExceptionType{
        DATA_NOT_AVAILABLE, INVALID_BOOK_QUANTITY;
    }

    public BookStoreException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
    public ExceptionType getType() {
        return type;
    }

}
