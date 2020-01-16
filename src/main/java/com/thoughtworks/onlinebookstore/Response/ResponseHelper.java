package com.thoughtworks.onlinebookstore.Response;


public class ResponseHelper {
    private Object status;
    private Object message;

    public ResponseHelper(Object status, Object message) {
        this.status = status;
        this.message = message;
    }
}
