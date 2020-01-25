package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.Response.ResponseHelper;
import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.dto.ConsumerDto;
import com.thoughtworks.onlinebookstore.model.CofirmOrderData;
import com.thoughtworks.onlinebookstore.service.OrderConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("/book")
@RestController

public class OrderController {
    @Autowired
    private OrderConfirmationService orderConfirmationService;

    @PostMapping("/order")
    public ResponseEntity confirmOrder(@RequestBody CofirmOrderData orderData)  {
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
