package com.thoughtworks.onlinebookstore.Response;

import org.springframework.stereotype.Component;

@Component
public class Response {
    private String statusMessage;
    private int statusCode;

    public Response() {
    }

    public Response(int code, String message) {
        this.statusMessage = message;
        this.statusCode = code;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
    public int getStatusCode() {
        return statusCode;
    }


    @Override
    public String toString() {
        return "Response [statusMessage=" + statusMessage + ", statusCode=" + statusCode + "]";
    }
}
