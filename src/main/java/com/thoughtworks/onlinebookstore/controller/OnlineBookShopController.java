package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Consumer;
import com.thoughtworks.onlinebookstore.service.OrderConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detailsPage")
public class OnlineBookShopController {

    @Autowired
    private OrderConfirmationService orderConfirmationService;

    @RequestMapping("/getUserDetails")
    public String addUserDetails(@RequestBody Consumer consumer) throws BookStoreException {
        try {
            return orderConfirmationService.setDetails(consumer);
        } catch (BookStoreException e) {
            throw new BookStoreException("invalid details..please check your entered data", BookStoreException.ExceptionType.INVALID_DETAIL);
        }
    }

}
