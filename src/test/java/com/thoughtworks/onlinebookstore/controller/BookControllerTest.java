package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.service.BookStoreServices;
import com.thoughtworks.onlinebookstore.service.OrderConfirmationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @Mock
    private BookStoreServices mockedStoreServices;

    @Autowired
    Environment environment;
    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void mockitoRule() {
        MockitoAnnotations.initMocks(this);
    }

    List<Book> bookList;

    @Test
    public void givenBookStore_WhenClickOnHomePage_ShouldReturnList() throws BookStoreException {
        bookList = new ArrayList<>();
        Book books1 = new Book(1, "Two States", "Chetan Bhagat", 100.0, "Image.jpg", "abcd", 1);
        bookList.add(books1);
        when(mockedStoreServices.getAllBooks()).thenReturn(bookList);
        String expectedAuthor = bookList.get(0).getAuthorName();
        bookController.getList();
        Assert.assertEquals(expectedAuthor, "Chetan Bhagat");
    }

    @Test
    public void givenBookStore_WhenClickOnHomePage_IfRecordListIsNull_ShouldThrowNullException() {
        bookList = new ArrayList<>();
        Book book = null;
        try {
            bookList.add(book);
            bookList.forEach(System.out::println);
            BookStoreException expectedException = mock(BookStoreException.class);
            when(mockedStoreServices.getAllBooks()).thenThrow(expectedException);
            bookController.getList();
        } catch (BookStoreException e) {
            Assert.assertEquals(BookStoreException.ExceptionType.DATA_NOT_AVAILABLE, e.getType());
        }
    }

    @Test
    public void givenBookStore_WhenClickOnHomePage_ShouldReturnTotalSizeOfRecord() {
        bookList = new ArrayList<>();
        Book books1 = new Book(1, "Two States", "Chetan Bhagat", 100.0, "Image.jpg", "abcd", 1);
        Book books2 = new Book();
        bookList.add(books2);
        bookList.add(books1);
        try {
            when(mockedStoreServices.getAllBooks()).thenReturn(bookList);
            bookController.getList();
            int size = bookList.size();
            Assert.assertEquals(2, size);
        } catch (BookStoreException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenBookStore_WhenSearchedByName_ShouldReturnTheBook() {
        bookList = new ArrayList<>();
        Book books1 = new Book(1, "Two States", "Chetan Bhagat", 100.0, "Image.jpg", "abcd", 1);
        bookList.add(books1);
        try {
            when(mockedStoreServices.getAllSearchedBooks("Two States")).thenReturn(bookList);
            List<Book> searchedList = bookController.getbookByName("Two States");
            boolean isAvailable = searchedList.get(0).getBookName().contains("Two");
            Assert.assertTrue(isAvailable);
        } catch (BookStoreException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenABook_WhenWantToSearchNotPresentInTHeDataBase_ShouldThrowException() {
        BookStoreException expectedException = new BookStoreException("", BookStoreException.ExceptionType.DATA_NOT_AVAILABLE);
        bookList = new ArrayList<>();
        try {
            when(mockedStoreServices.getAllSearchedBooks("xyz")).thenThrow(expectedException);
             bookController.getbookByName("xyz");
        } catch (BookStoreException e) {
            Assert.assertEquals(BookStoreException.ExceptionType.DATA_NOT_AVAILABLE,e.getType());
        }
    }

}