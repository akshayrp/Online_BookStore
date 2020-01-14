package com.thoughtworks.onlinebookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnlineBookShopController {

    @RequestMapping("/getUserDetails")
    public String addUserDetails(@RequestParam(value = "name") String name) {
        return name;
    }

}
