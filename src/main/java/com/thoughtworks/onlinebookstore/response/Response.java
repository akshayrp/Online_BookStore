package com.thoughtworks.onlinebookstore.response;

public class Response {
    private final Object status;
    private final Object result;

    public Response(Object status, Object result) {
        this.status = status;
        this.result = result;
    }
}
