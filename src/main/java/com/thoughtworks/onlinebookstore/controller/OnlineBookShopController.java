package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.Response.Response;
import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.model.Books;
import com.thoughtworks.onlinebookstore.model.Consumer;
import com.thoughtworks.onlinebookstore.service.IBookStoreServices;
import com.thoughtworks.onlinebookstore.service.OrderConfirmationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

//import com.thoughtworks.onlinebookstore.dto.BookDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/TallTalesBooks")
@RestController
public class OnlineBookShopController {

    @Autowired
    private OrderConfirmationService orderConfirmationService;
    @Autowired
    private IBookStoreServices bookStoreServices;

    @PostMapping()  @ApiOperation("Api for Add Book")
    public ResponseEntity<Response> addBook(@Valid @RequestBody BookDto book) {
        try {
            bookStoreServices.addBook(book);
            return new ResponseEntity("Book Added Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("something went wrong ->" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public List<Books> getList() throws BookStoreException {
        List<Books> bookList = null;
        try {
            bookList = orderConfirmationService.getAllBooks();
            return bookList;
        } catch (BookStoreException e) {
            throw new BookStoreException("data not available",BookStoreException.ExceptionType.DATA_NOT_AVAILABLE);
        }
    }

    @GetMapping("/get/{id}")
    public Book getById(@PathVariable int id, @RequestParam(value = "quantity") int quantity) {
        return orderConfirmationService.getBookById(id,quantity);
    }

    @RequestMapping("/getUserDetails")
    public String addUserDetails(@RequestBody Consumer consumer) throws BookStoreException {
        try {
            return orderConfirmationService.setDetails(consumer);
        } catch (BookStoreException e) {
            throw new BookStoreException("invalid details..please check your entered data", BookStoreException.ExceptionType.INVALID_DETAIL);
        }
    }

    @GetMapping("/confirmOrder")
    public Response confirmOrder() {
    return orderConfirmationService.confirmOrderAndSendMail();
    }

}
