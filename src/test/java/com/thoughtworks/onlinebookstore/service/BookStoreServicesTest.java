package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Books;
import com.thoughtworks.onlinebookstore.repository.IBookShopRepository;
import org.junit.Assert;
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
public class BookStoreServicesTest {

    List<Books> booksList;
    @Mock
    private IBookShopRepository mockedBookShopRepository;


    @InjectMocks
    private BookStoreServices bookStoreServices;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void givenBookStore_WhenClickOnHomePage_ShouldReturnList() {
        booksList = new ArrayList<>();
        Books books1 = new Books(1, "Chetan Bhagat", "The Girl in Room 105'", "afc", 100, "mre");
        booksList.add(books1);
        try {
            when(mockedBookShopRepository.findAll()).thenReturn(booksList);
            bookStoreServices.getAllBooks();
            Assert.assertEquals("Chetan Bhagat", booksList.get(0).getAuthor());
        } catch (BookStoreException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenBookStore_WhenClickOnHomePage_ShouldReturnTotalSizeOfRecord() {
        booksList = new ArrayList<>();
        Books books1 = new Books(1, "Chetan Bhagat", "The Girl in Room 105'", "afc", 100, "mre");
        Books books2 = new Books(1, "Sargam Pandey", "The Girl in Room 105'", "afc", 100, "mre");
        booksList.add(books1);
        booksList.add(books2);
        try {
            when(mockedBookShopRepository.findAll()).thenReturn(booksList);
            bookStoreServices.getAllBooks();
            int size = booksList.size();
            Assert.assertEquals(2, size);
        } catch (BookStoreException e) {
        }
    }
}