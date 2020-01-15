package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.model.Books;
import com.thoughtworks.onlinebookstore.service.IBookShopServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OnlineBookShopController {

    @Autowired
    IBookShopServices bookShopServices;

    @GetMapping("/list")
    public List<Books> getList() {
        List<Books> bookList = bookShopServices.getAllBooks();
        return bookList;
    }

    public void setService(IBookShopServices bookShopService) {
        bookShopServices = bookShopService;
    }
}