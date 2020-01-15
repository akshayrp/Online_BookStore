package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;

public interface IBookStoreServices {

    boolean addBook(BookDto bookDto) throws BookStoreException;
}
