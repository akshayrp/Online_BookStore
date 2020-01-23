package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;

import java.util.List;

public interface IBookStoreServices {

    boolean addBook(BookDto bookDto) throws BookStoreException;

    List<Book> getAllBooks() throws BookStoreException;

    Book getBookById(int id);

    public void updateQuantity(List<Book> bookList) throws BookStoreException;

    List<Book> getAllSearchedBooks(String bookName) throws BookStoreException;
}
