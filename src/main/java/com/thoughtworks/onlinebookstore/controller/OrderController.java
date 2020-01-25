package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.dto.ConsumerDto;
import com.thoughtworks.onlinebookstore.model.ConfirmOrderData;
import com.thoughtworks.onlinebookstore.service.OrderConfirmationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/book")
@RestController

public class OrderController {
    @Autowired
    private OrderConfirmationService orderConfirmationService;

    @PostMapping("/order")
    @ApiOperation("Api to confirm order")
    public ResponseEntity confirmOrder(@RequestBody ConfirmOrderData orderData)  {
        List<BookDto> bookList = orderData.getBookList();
        ConsumerDto consumerDto = orderData.getConsumerDto();
        try {
            orderConfirmationService.confirmOrderAndSendMail(consumerDto, bookList);
            return new ResponseEntity("Order Confirmed", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("something went wrong ->" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
