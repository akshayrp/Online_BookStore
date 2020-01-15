package com.thoughtworks.onlinebookstore.exception;

public class BookStoreException extends Exception {
  public ExceptionType type;

    public enum ExceptionType{
        INVALID_DETAIL;
    }

    public BookStoreException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
