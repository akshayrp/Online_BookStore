package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.dto.ConsumerDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.ConfirmOrderData;
import com.thoughtworks.onlinebookstore.service.OrderConfirmationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@RequestMapping("/book")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {
    @Autowired
    private OrderConfirmationService orderConfirmationService;

    @PostMapping("/order")
    @ApiOperation("Api to confirm order")
    public ResponseEntity confirmOrder(@Valid @RequestBody ConfirmOrderData orderData)  {
        List<BookDto> bookList = orderData.getBookList();
        ConsumerDto consumerDto = orderData.getConsumerDto();
        try {
            orderConfirmationService.confirmOrderAndSendMail(consumerDto, bookList);
            return new ResponseEntity("Order Confirmed", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("something went wrong ->" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{email}")
    @ApiOperation("Api to get order ID")
    public int getOrderId(@Valid @NotNull @Pattern(regexp =  ("^[a-zA-Z0-9]([-._+]{0,1}[a-zA-Z0-9])*[@]{1}[a-zA-Z0-9]{1,}[.]{1}[a-zA-Z]{2,3}([.]{1}[a-zA-Z]{2,3}){0,1}$"),
            message ="INVALID EMAIL") @PathVariable String email) throws BookStoreException {
        try {
            return orderConfirmationService.getOrderId(email);
        } catch (BookStoreException e) {
            throw  new BookStoreException("something went wrong ->" + e.getMessage(), BookStoreException.ExceptionType.DATA_NOT_AVAILABLE);
        }
    }

}
