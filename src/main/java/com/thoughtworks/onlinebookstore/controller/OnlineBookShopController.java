package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.Response.ResponseHelper;
import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.service.IBookStoreServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/book")
@RestController
public class OnlineBookShopController {

    @Autowired
    private IBookStoreServices bookStoreServices;

    @PostMapping("/post")
    @ApiOperation("Api for Add Book")
    public ResponseEntity<ResponseHelper> addBook(@Valid @RequestBody BookDto book) {
        System.out.println("sout to add commit msg into github and run build on jenkins");
        try {
            bookStoreServices.addBook(book);
            return new ResponseEntity("Book Added Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("something went wrong ->" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public List<Book> getList() throws BookStoreException {
        try {
            return bookStoreServices.getAllBooks();
        } catch (BookStoreException e) {
            throw new BookStoreException("data not available", BookStoreException.ExceptionType.DATA_NOT_AVAILABLE);
        }
    }

    @GetMapping("get/{bookName}")
    public List<Book> getbookByName(@PathVariable String bookName) throws BookStoreException {
        try {
            return bookStoreServices.getAllSearchedBooks(bookName);
        } catch (BookStoreException e) {
            throw  new BookStoreException(e.getMessage(), BookStoreException.ExceptionType.DATA_NOT_AVAILABLE);
        }
    }
}
