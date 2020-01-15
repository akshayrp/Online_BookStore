package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Books;
import com.thoughtworks.onlinebookstore.service.BookStoreServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OnlineBookShopController {

    @Autowired
    BookStoreServices bookStoreServices;

    @GetMapping("/list")
    public List<Books> getList() throws BookStoreException {
        List<Books> bookList = null;
        try {
            bookList = bookStoreServices.getAllBooks();
            return bookList;
        } catch (BookStoreException e) {
            throw new BookStoreException("data not available",BookStoreException.ExceptionType.DATA_NOT_AVAILABLE);
        }
    }
}