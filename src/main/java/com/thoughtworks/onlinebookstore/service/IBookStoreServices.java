package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;

import java.util.List;

public interface IBookStoreServices {

    boolean addBook(BookDto bookDto) throws BookStoreException;

    List<Book> getAllBooks() throws BookStoreException;

    Book getBookById(int id);

    void updateQuantity(int id, int purchasedQuantity, Book book) throws BookStoreException;

    List<Book> getBookFromCart(List<Book> bookList);
}
