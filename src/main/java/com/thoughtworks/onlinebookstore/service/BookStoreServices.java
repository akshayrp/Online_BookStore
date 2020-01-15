package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.repository.IBookStoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookStoreServices implements IBookStoreServices {

    @Autowired
    private IBookStoreRepository bookShopRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public boolean addBook(BookDto bookDto) throws BookStoreException {
        if (bookDto == null)
            throw new BookStoreException("Book data cannot be null", BookStoreException.ExceptionType.CANT_ADD_NULL_DATA);
        Book book = mapper.map(bookDto, Book.class);
        bookShopRepository.save(book);
        return true;
    }
}
