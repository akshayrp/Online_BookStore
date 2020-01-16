package com.thoughtworks.onlinebookstore.Response;


public class ResponseHelper {
    public static Response statusResponse(int code, String message) {
        Response statusResponse = new Response(code,message);
        return statusResponse;
    }
}
