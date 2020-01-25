package com.thoughtworks.onlinebookstore.exception;

import org.springframework.http.ResponseEntity;

public class BookStoreException extends Exception {
    private int requestStatus;
    private ResponseEntity responseEntity;
    private ExceptionType type;

    public enum ExceptionType{
        DATA_NOT_AVAILABLE, INVALID_BOOK_QUANTITY, MAIL_NOT_SENT;
    }

    public BookStoreException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
    public ExceptionType getType() {
        return type;
    }

}
