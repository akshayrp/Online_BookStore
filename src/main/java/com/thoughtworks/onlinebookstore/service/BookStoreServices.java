package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.repository.IBookStoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookStoreServices implements IBookStoreServices {

    @Autowired
    private IBookStoreRepository bookShopRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public boolean addBook(BookDto bookDto) {
        Book book = mapper.map(bookDto, Book.class);
        Optional<Book> bookExist = bookShopRepository.findByAuthorNameAndBookEditionAndBookName(book.getBookName(),
                book.getAuthorName(), book.getBookEdition());
        if (!bookExist.isPresent()) {
            bookShopRepository.save(book);
            return true;
        }
        bookExist.map(storedBook -> {
            storedBook.setQuantity(storedBook.getQuantity() + bookDto.getQuantity());
            bookShopRepository.save(storedBook);
            return storedBook;
        });
        return true;
    }
}
