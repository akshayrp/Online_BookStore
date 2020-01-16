package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.model.Consumer;
import com.thoughtworks.onlinebookstore.repository.IBookStoreRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderConfirmationServiceTest {

    List<Book> booksList;
    @Mock
    private IBookStoreRepository mockedBookShopRepository;

    @InjectMocks
    private OrderConfirmationService orderConfirmationService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenBookStore_WhenClickOnHomePage_ShouldReturnList() {
        booksList = new ArrayList<>();
        Book books1 = new Book(1, "Chetan Bhagat", "The Girl in Room 105'", "afc", 100.0, "mre", 10);
        booksList.add(books1);
        when(mockedBookShopRepository.findAll()).thenReturn(booksList);
        orderConfirmationService.getAllBooks();
        Assert.assertEquals("Chetan Bhagat", booksList.get(0).getAuthorName());
    }

    @Test
    public void givenBookStore_WhenClickOnHomePage_ShouldReturnTotalSizeOfRecord() {
        booksList = new ArrayList<>();
        Book books1 = new Book(1, "Chetan Bhagat", "The Girl in Room 105'", "afc", 100.0, "mre", 10);
        Book books2 = new Book(1, "Sargam Pandey", "The Girl in Room 105'", "afc", 100.0, "mre", 10);
        booksList.add(books1);
        booksList.add(books2);
        when(mockedBookShopRepository.findAll()).thenReturn(booksList);
        orderConfirmationService.getAllBooks();
        int size = booksList.size();
        Assert.assertEquals(2, size);
    }

    @Test
    public void name() {
        Consumer mockedConsumer = new Consumer("Karan", "karan24@gmail.com", "kharadi", "201901", "India");
        double totalPrice = orderConfirmationService.getTotalPrice();
    }
}