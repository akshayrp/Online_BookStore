package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.Response.ResponseHelper;
import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.dto.ConsumerDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
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

@RequestMapping("/TallTalesBooks")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class OnlineBookShopController {

    @Autowired
    private OrderConfirmationService orderConfirmationService;
    @Autowired
    private IBookStoreServices bookStoreServices;

    @PostMapping("/addBook")
    @ApiOperation("Api for Add Book")
    public ResponseEntity<ResponseHelper> addBook(@Valid @RequestBody BookDto book) {
        try {
            bookStoreServices.addBook(book);
            return new ResponseEntity("Book Added Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("something went wrong ->" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public List<Book> getList() throws BookStoreException {
        List<Book> bookList = null;
        bookList = bookStoreServices.getAllBooks();
        return bookList;
    }

    @GetMapping("/get/{id}/{quantity}")
    public Book getById(@PathVariable int id, @PathVariable int quantity) {
        return orderConfirmationService.getPurchasingBook(id, quantity);
    }

//    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/AddUserDetails")
    public Consumer addUserDetails(@Valid @RequestBody ConsumerDto consumer) {
        return orderConfirmationService.setDetails(consumer);
    }

    @PostMapping("/confirmOrder/{consumerId}")
    public String confirmOrder(@Valid @PathVariable Long consumerId) {
        ResponseHelper responseHelper = orderConfirmationService.confirmOrderAndSendMail(consumerId);
        return responseHelper.toString();
    }

}
