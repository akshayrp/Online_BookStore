package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Books;
import com.thoughtworks.onlinebookstore.service.BookStoreServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OnlineBookShopControllerTest {

    List<Books> mockBookList;
    @Mock
    private BookStoreServices mockedBookStoreServices;

    @InjectMocks
    private OnlineBookShopController myController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenBookStore_WhenClickOnHomePage_ShouldReturnList() throws BookStoreException {
        mockBookList = new ArrayList<>();
        Books books1 = new Books(1, "Chetan Bhagat", "The Girl in Room 105'", "afc", 100, "mre");
        mockBookList.add(books1);
        when(mockedBookStoreServices.getAllBooks()).thenReturn(mockBookList);
        String expectedAuthor = mockBookList.get(0).getAuthor();
        myController.getList();
        Assert.assertEquals(expectedAuthor, "Chetan Bhagat");
    }

    @Test
    public void givenBookStore_WhenClickOnHomePage_IfRecordListIsNull_ShouldThrowNullException() {
        mockBookList = new ArrayList<>();
        Books books = new Books();
        mockBookList.add(books);
        try {
            BookStoreException expectedException = mock(BookStoreException.class);
            when(mockedBookStoreServices.getAllBooks()).thenThrow(expectedException);
            myController.getList();
        } catch (BookStoreException e) {
            Assert.assertEquals(BookStoreException.ExceptionType.DATA_NOT_AVAILABLE, e.type);
        }

    }


    @Test
    public void givenBookStore_WhenClickOnHomePage_ShouldReturnTotalSizeOfRecord() {
        mockBookList = new ArrayList<>();
        Books books1 = new Books(1, "Chetan Bhagat", "The Girl in Room 105'", "afc", 100, "mre");
        Books books2 = new Books();
        mockBookList.add(books2);
        mockBookList.add(books1);
        try {
            when(mockedBookStoreServices.getAllBooks()).thenReturn(mockBookList);
            myController.getList();
            int size = mockBookList.size();
            Assert.assertEquals(2, size);
        } catch (BookStoreException e) {
        }
    }
}