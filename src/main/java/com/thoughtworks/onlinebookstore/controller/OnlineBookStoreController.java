package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.response.Response;
import com.thoughtworks.onlinebookstore.service.IBookStoreServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstore")
public class OnlineBookStoreController {

    @Autowired
    private IBookStoreServices bookStoreServices;

    @PostMapping()
    public Response addbook(@RequestBody BookDto book) {
        try {
            bookStoreServices.addBook(book);
            return new Response(200, "Book Added Successfully");
        } catch (Exception e) {
            return new Response(500, "Something went Wrong...<br>" + e.getMessage());
        }
    }
}
