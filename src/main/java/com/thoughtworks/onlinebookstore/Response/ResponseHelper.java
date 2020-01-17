package com.thoughtworks.onlinebookstore.Response;


public class ResponseHelper {
    private int status;
    private String message;

    public ResponseHelper(int status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseHelper{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
