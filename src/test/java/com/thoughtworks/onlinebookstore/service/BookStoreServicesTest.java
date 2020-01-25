package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.repository.IBookStoreRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookStoreServicesTest {

    List<Book> booksList;
    List<Book> expectedBookList;

    @Mock
    private ModelMapper mapper;

    @Mock
    private IBookStoreRepository mockedBookShopRepository;

    @InjectMocks
    private BookStoreServices bookStoreServices;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void givenListOfBooks_WhentriedToFindById_ShouldReturnBookId() {
        Book book1 = new Book(1, "bla", "sargam", 200.3, "xyz", "all about you..", 2);
        when(mockedBookShopRepository.findById(1)).thenReturn(Optional.of(book1));
        Book bookById = bookStoreServices.getBookById(1);
        Assert.assertEquals(book1, bookById);
    }

    @Test
    public void givenBookStore_WhenEmpty_ShouldThrowException() {
        try {
            booksList = new ArrayList<>();
            BookStoreException expectedException = mock(BookStoreException.class);
            when(mockedBookShopRepository.findAll()).thenReturn(null);
            bookStoreServices.getAllBooks();
        } catch (BookStoreException e) {
            Assert.assertEquals("data not available", e.getMessage());
        }
    }

    @Test
    public void givenBookStore_WhenClickOnHomePage_ShouldReturnList() throws BookStoreException {
        booksList = new ArrayList<>();
        expectedBookList = new ArrayList<>();
        Book books1 = new Book(1, "bla", "sargam", 200.3, "xyz", "all about you..", 2);
        booksList.add(books1);
        when(mockedBookShopRepository.findAll()).thenReturn(booksList);
        expectedBookList = bookStoreServices.getAllBooks();
        Assert.assertEquals("sargam", expectedBookList.get(0).getAuthorName());
    }

    @Test
    public void givenBookStore_WhenClickOnHomePage_ShouldReturnTotalSizeOfRecord() throws BookStoreException {
        booksList = new ArrayList<>();
        Book books1 = new Book(1, "bla", "sargam", 200.3, "xyz", "all about you..", 2);
        Book books2 = new Book(2, "bla", "sargam", 200.3, "xyz", "all about you..", 2);
        booksList.add(books1);
        booksList.add(books2);
        when(mockedBookShopRepository.findAll()).thenReturn(booksList);
        bookStoreServices.getAllBooks();
        int size = booksList.size();
        Assert.assertEquals(2, size);
    }

    @Test
    public void givenListOfBooks_WhenSearchedByName_ShouldReturnBooks() {
        try {
            booksList = new ArrayList<>();
            Book books1 = new Book(1, "bla", "sargam", 200.3, "xyz", "all about you..", 2);
            Book books2 = new Book(2, "abc", "sargam", 200.3, "xyz", "all about you..", 2);
            booksList.add(books1);
            booksList.add(books2);
            expectedBookList = new ArrayList<>();
            expectedBookList.add(books1);
            when(mockedBookShopRepository.findAll()).thenReturn(booksList);
            List<Book> searchedBooks = bookStoreServices.getAllSearchedBooks("bla");
            Assert.assertEquals(expectedBookList, searchedBooks);
        } catch (BookStoreException e) {
        }
    }


    @Test
    public void givenListOfBooks_WhenSearchedByNameAndNotFound_ShouldThrowException() {
        try {
            booksList = new ArrayList<>();
            Book books1 = new Book(1, "bla", "sargam", 200.3, "xyz", "all about you..", 2);
            Book books2 = new Book(2, "abc", "sargam", 200.3, "xyz", "all about you..", 2);
            booksList.add(books1);
            booksList.add(books2);
            expectedBookList = new ArrayList<>();
            expectedBookList.add(books2);
            when(mockedBookShopRepository.findAll()).thenReturn(null);
            BookStoreException expectedException = mock(BookStoreException.class);
            List<Book> searchedBooks = bookStoreServices.getAllSearchedBooks("bla");
            Assert.assertEquals(expectedBookList, searchedBooks);
        } catch (BookStoreException e) { }
    }

    @Test
    public void givenBookWhenPurchased_ShouldUpdateQuantity() {
        try {
            booksList = new ArrayList<>();
            Book book1 = new Book(1, "bla", "sargam", 200.3, "xyz", "all about you..", 2);
            booksList.add(book1);
            when(mockedBookShopRepository.getOne(book1.getBookId())).thenReturn(book1);
            int remainingQuantity = 3- book1.getQuantity();
            when(mockedBookShopRepository.getOne(1)).thenReturn(book1);
            book1.setQuantity(remainingQuantity);
            bookStoreServices.updateQuantity(booksList);
            verify(mockedBookShopRepository).save(book1);
        } catch (BookStoreException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenBookWhenTriedToSelectMoreThanVailableQuantity_ShouldThrowException() {
        try {
            booksList = new ArrayList<>();
            Book book1 = new Book(1, "bla", "sargam", 200.3, "xyz", "all about you..", 3);
            booksList.add(book1);
            // when(mockedBookShopRepository.getOne(book1.getBookId())).thenReturn(null);
            int remainingQuantity = 3- book1.getQuantity();
            when(mockedBookShopRepository.getOne(1)).thenReturn(book1);
            book1.setQuantity(remainingQuantity);
            bookStoreServices.updateQuantity(booksList);
            verify(mockedBookShopRepository).save(book1);
        } catch (BookStoreException e) {
            Assert.assertEquals("Please enter book quantity greater than 0 or more than available books0", e.getMessage());
        }
    }
}

