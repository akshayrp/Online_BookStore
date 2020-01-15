package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Books;
import com.thoughtworks.onlinebookstore.repository.IBookShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreServices {

    @Autowired
    IBookShopRepository bookShopRepository;

    public List<Books> getAllBooks() throws BookStoreException {
        List<Books> booksList = bookShopRepository.findAll();
        if (booksList == null) {
            throw new BookStoreException("data not available",BookStoreException.ExceptionType.DATA_NOT_AVAILABLE);
        }
        return booksList;
    }
}
