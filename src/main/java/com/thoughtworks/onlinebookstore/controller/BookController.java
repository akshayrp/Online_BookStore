package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.service.IBookStoreServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    private IBookStoreServices bookStoreServices;

    @GetMapping("/")
    @ApiOperation("Api to get Booklist")
    public List<Book> getList() throws BookStoreException {
        try {
            return bookStoreServices.getAllBooks();
        } catch (BookStoreException e) {
            throw new BookStoreException("data not available", BookStoreException.ExceptionType.DATA_NOT_AVAILABLE);
        }
    }

    @GetMapping("get/{bookName}")
    @ApiOperation("Api to find book by name")
    public List<Book> getbookByName(@PathVariable String bookName) throws BookStoreException {
        try {
            return bookStoreServices.getAllSearchedBooks(bookName);
        } catch (BookStoreException e) {
           throw  new BookStoreException("something went wrong ->" + e.getMessage(), BookStoreException.ExceptionType.DATA_NOT_AVAILABLE);
        }
    }
}
