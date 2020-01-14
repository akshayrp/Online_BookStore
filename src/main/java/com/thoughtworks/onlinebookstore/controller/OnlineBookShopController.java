package com.thoughtworks.onlinebookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnlineBookShopController {


    @GetMapping("/list")
    public String getList() {
        return "Hello World !";
    }
}