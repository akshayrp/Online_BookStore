package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.Response.ResponseHelper;
import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.dto.ConsumerDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.service.IBookStoreServices;
import com.thoughtworks.onlinebookstore.service.OrderConfirmationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/TallTalesBooks")
@RestController
public class OnlineBookShopController {

    @Autowired
    private OrderConfirmationService orderConfirmationService;
    @Autowired
    private IBookStoreServices bookStoreServices;

    @PostMapping("/addBook")
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

    @GetMapping("/list")
    public List<Book> getList() throws BookStoreException {
        try {
            return bookStoreServices.getAllBooks();
        } catch (BookStoreException e) {
            throw new BookStoreException("data not available", BookStoreException.ExceptionType.DATA_NOT_AVAILABLE);
        }
    }


    @PostMapping("/confirmOrder/")
    public String getCart(@RequestBody CofirmOrderData orderData) {
        List<BookDto> bookList = orderData.getBookList();
        ConsumerDto consumerDto = orderData.getConsumerDto();
        ResponseHelper responseHelper = orderConfirmationService.confirmOrderAndSendMail(consumerDto, bookList);
        return responseHelper.toString();
    }

    @GetMapping("bookByName/{bookName}")
    public List<Book> getbookByName(@PathVariable String bookName) throws BookStoreException {
        try {
            return bookStoreServices.getAllSearchedBooks(bookName);
        } catch (BookStoreException e) {
            throw  new BookStoreException("No Data Available", BookStoreException.ExceptionType.DATA_NOT_AVAILABLE);
        }
    }
}
