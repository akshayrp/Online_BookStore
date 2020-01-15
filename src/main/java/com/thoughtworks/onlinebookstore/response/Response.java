package com.thoughtworks.onlinebookstore.response;

public class Response {
    private final int status;
    private final String result;

    public Response(int status, String result) {
        this.status = status;
        this.result = result;
    }
}
