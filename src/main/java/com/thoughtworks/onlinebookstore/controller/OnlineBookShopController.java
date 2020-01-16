package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.Response.Response;
import com.thoughtworks.onlinebookstore.dto.MailDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.model.Books;
import com.thoughtworks.onlinebookstore.model.Consumer;
import com.thoughtworks.onlinebookstore.service.OrderConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/TallTalesBooks")
@RestController
public class OnlineBookShopController {

    @Autowired
    private OrderConfirmationService orderConfirmationService;

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

    @PostMapping(value = "/getUserDetails")
    public String addUserDetails(@RequestBody Consumer consumer) throws BookStoreException {
        try {
            return orderConfirmationService.setDetails(consumer);
        } catch (BookStoreException e) {
            throw new BookStoreException("invalid details..please check your entered data", BookStoreException.ExceptionType.INVALID_DETAIL);
        }
    }

    @PostMapping("/confirmOrder/{consumerId}")
    public Response confirmOrder(@PathVariable Long consumerId) {
    return orderConfirmationService.confirmOrderAndSendMail(consumerId);
    }


    /*@GetMapping("/orderSummary/{id}")
    public void getPrice(@PathVariable int id, @RequestParam(value = "quantity") int quantity ,@RequestBody Consumer consumer){
        orderConfirmationService.getTotalPrice(id,consumer,quantity);
    }
*/

}
