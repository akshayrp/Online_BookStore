package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.response.Response;
import com.thoughtworks.onlinebookstore.service.IBookStoreServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/bookstore")
public class OnlineBookStoreController {

    @Autowired
    private IBookStoreServices bookStoreServices;

    @PostMapping()
    @ApiOperation("Api for Add Book")
    public ResponseEntity<Response> addBook(@Valid @RequestBody BookDto book) {
        try {
            bookStoreServices.addBook(book);
            return new ResponseEntity("Book Added Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("something went wrong ->" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
