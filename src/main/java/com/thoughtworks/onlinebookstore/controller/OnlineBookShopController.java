package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.Response.Response;
import com.thoughtworks.onlinebookstore.service.OrderConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/TallTalesBooks")
@RestController
public class OnlineBookShopController {

    @Autowired
    private OrderConfirmationService orderConfirmationService;

    @GetMapping("/confirmOrder")
    public Response confirmOrder() {
    return orderConfirmationService.confirmOrderAndSendMail();
    }
}
