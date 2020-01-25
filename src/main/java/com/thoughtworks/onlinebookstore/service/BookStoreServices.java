package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.repository.IBookStoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookStoreServices implements IBookStoreServices {

    @Autowired
    private IBookStoreRepository bookShopRepository;

    @Autowired
    private ModelMapper mapper;
    private Book book;

    @Override
    public Book getBookById(int id) {
        Book book = bookShopRepository.findById(id).get();
        return book;
    }

    @Override
    public List<Book> getAllSearchedBooks(String bookName) throws BookStoreException {
        try {
            List<Book> allBooks = getAllBooks();
            return allBooks.stream().filter(book -> book.getBookName().contains(bookName)).collect(Collectors.toList());
        } catch (BookStoreException e) {
            throw new BookStoreException("data not available", BookStoreException.ExceptionType.DATA_NOT_AVAILABLE);
        }
    }

    @Override
    public List<Book> getAllBooks() throws BookStoreException {
        List<Book> booksList = bookShopRepository.findAll();
        if (booksList == null) {
            throw new BookStoreException("data not available", BookStoreException.ExceptionType.DATA_NOT_AVAILABLE);
        }
        return booksList;
    }

    public void updateQuantity(List<Book> bookList) throws BookStoreException {
        for (Book book : bookList) {
            int dbQuantity = bookShopRepository.getOne(book.getBookId()).getQuantity();
            if (dbQuantity < book.getQuantity() || book.getQuantity() < 1)
                throw new BookStoreException("Please enter book quantity greater than 0 or more than available books"
                        + dbQuantity, BookStoreException.ExceptionType.INVALID_BOOK_QUANTITY);
            int remainingQuantity = dbQuantity - book.getQuantity();
            Book getBookById = bookShopRepository.getOne(book.getBookId());
            getBookById.setQuantity(remainingQuantity);
            bookShopRepository.save(getBookById);
        }
    }
}
