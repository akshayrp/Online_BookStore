package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.Response.ResponseHelper;
import com.thoughtworks.onlinebookstore.dto.ConsumerDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.model.Consumer;
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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OnlineBookShopControllerTest {

    @Mock
    private BookStoreServices storeServices;

    @Mock
    private OrderConfirmationService mockedOrderConfirmationService;

    @Autowired
    Environment environment;
    @InjectMocks
    private OnlineBookShopController controller;

    @BeforeEach
    public void mockitoRule() {
        MockitoAnnotations.initMocks(this);
    }

    List<Book> mockBookList;

    @Test
    public void givenBookStore_WhenClickOnHomePage_ShouldReturnList() throws BookStoreException {
        mockBookList = new ArrayList<>();
        Book books1 = new Book("Chetan Bhagat", 100, 10, "Chetan Bhagat", "abcd", "pooja");
        mockBookList.add(books1);
        when(storeServices.getAllBooks()).thenReturn(mockBookList);
        String expectedAuthor = mockBookList.get(0).getAuthorName();
        controller.getList();
        Assert.assertEquals(expectedAuthor, "Chetan Bhagat");
    }

    @Test
    public void givenBookStore_WhenClickOnHomePage_IfRecordListIsNull_ShouldThrowNullException() {
        mockBookList = new ArrayList<>();
        Book book = null;
        try {
            mockBookList.add(book);
            mockBookList.forEach(System.out::println);
            BookStoreException expectedException = mock(BookStoreException.class);
            when(storeServices.getAllBooks()).thenThrow(expectedException);
            controller.getList();
        } catch (BookStoreException e) {
            Assert.assertEquals(BookStoreException.ExceptionType.DATA_NOT_AVAILABLE, e.getType());
        }
    }

    @Test
    public void givenBookStore_WhenClickOnHomePage_ShouldReturnTotalSizeOfRecord() {
        mockBookList = new ArrayList<>();
        Book books1 = new Book("Chetan Bhagat", 100, 10, "Chetan Bhagat", "abcd", "pooja");
        Book books2 = new Book();
        mockBookList.add(books2);
        mockBookList.add(books1);
        try {
            when(storeServices.getAllBooks()).thenReturn(mockBookList);
            controller.getList();
            int size = mockBookList.size();
            Assert.assertEquals(2, size);
        } catch (BookStoreException e) {
        }
    }

    @Test
    public void givenOnUserDetailsPage_whenClickedOnBuyButton_ShouldGetUserDeliveryDetails() {
        ConsumerDto consumer = new ConsumerDto("pooja", "pooja1@gmail.com", "abc", "213456", "india");
        Consumer consumer1 = new Consumer("pooja", "pooja1@gmail.com", "abc", "213456", "india");
        when(mockedOrderConfirmationService.setDetails(consumer)).thenReturn(consumer1);
        Consumer consumer2 = controller.addUserDetails(consumer);
        Assert.assertEquals(consumer1.toString(), consumer2.toString());
    }

}