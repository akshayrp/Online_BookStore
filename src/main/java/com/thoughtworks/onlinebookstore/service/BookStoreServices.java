package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.repository.IBookStoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookStoreServices implements IBookStoreServices {

    @Autowired
    private IBookStoreRepository bookShopRepository;

    @Autowired
    private ModelMapper mapper;
    private Book book;

    @Override
    public boolean addBook(BookDto bookDto) {
        Book book = mapper.map(bookDto, Book.class);
        Optional<Book> bookExist = bookShopRepository.findByAuthorNameAndBookName(book.getBookName(),
                book.getAuthorName());
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

    @Override
    public Book getBookById(int id) {
        Book book = bookShopRepository.findById(id).get();
        return book;
    }

    @Override
    public void updateQuantity(int id, int purchasedQuantity,Book book) throws BookStoreException {
        int dbQuantity = bookShopRepository.findById(book.getBookId()).get().getQuantity();
        if (dbQuantity < purchasedQuantity || purchasedQuantity < 1) {
            throw new BookStoreException("Please enter book quantity greater than 0 or more than available books" + dbQuantity, BookStoreException.ExceptionType.INVALID_BOOK_QUANTITY);
        }
        int remainingQuantity = dbQuantity - book.getQuantity();
        Book getBookById = bookShopRepository.findById(book.getBookId()).get();
        getBookById.setQuantity(remainingQuantity);
        bookShopRepository.save(getBookById);
    }

    @Override
    public List<Book> getAllBooks() throws BookStoreException {
        List<Book> booksList = bookShopRepository.findAll();
        if (booksList == null) {
            throw new BookStoreException("data not available", BookStoreException.ExceptionType.DATA_NOT_AVAILABLE);
        }
        return booksList;
    }
}
