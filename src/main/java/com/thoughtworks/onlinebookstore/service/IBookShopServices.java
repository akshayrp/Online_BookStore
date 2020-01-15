package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.model.Books;
import com.thoughtworks.onlinebookstore.repository.IBookShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IBookShopServices {

    @Autowired
    IBookShopRepository bookShopRepository;

    public List<Books> getAllBooks() {
        List<Books> booksList = bookShopRepository.findAll();
        return booksList;
    }

}
