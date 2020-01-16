package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.Response.ResponseHelper;
import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.model.Consumer;
import com.thoughtworks.onlinebookstore.service.IBookStoreServices;
import com.thoughtworks.onlinebookstore.service.IConsumerServices;
import com.thoughtworks.onlinebookstore.service.OrderConfirmationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/TallTalesBooks")
@RestController
public class OnlineBookShopController {

    @Autowired
    private OrderConfirmationService orderConfirmationService;
    @Autowired
    private IBookStoreServices bookStoreServices;
    @Autowired
    private IConsumerServices consumerServices;

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
    @ApiOperation("Api for getting all Books In Inventory")
    public List<Book> getList() throws BookStoreException {
        return bookStoreServices.getAllBooks();
    }

    @GetMapping("/get/{id}/{quantity}")
    @ApiOperation("Api for Get Book")
    public Book getById(@PathVariable int id, @PathVariable int quantity) {
        return orderConfirmationService.getPurchasingBook(id, quantity);
    }

    @PostMapping(value = "/getUserDetails")
    @ApiOperation("Api for Add Consumer")
    public Consumer addUserDetails(@Valid @RequestBody Consumer consumer) {
        return consumerServices.setDetails(consumer);
    }

    @PostMapping("/confirmOrder/{consumerId}")
    @ApiOperation("Api for Confirm Order")
    public ResponseHelper confirmOrder(@Valid @PathVariable Long consumerId) {
        return orderConfirmationService.confirmOrderAndSendMail(consumerId);
    }
}
