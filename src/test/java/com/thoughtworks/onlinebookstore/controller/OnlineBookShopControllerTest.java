package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.Response.Response;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Books;
import com.thoughtworks.onlinebookstore.model.Consumer;
import com.thoughtworks.onlinebookstore.service.OrderConfirmationService;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OnlineBookShopControllerTest {

    @Mock
    private OrderConfirmationService orderConfirmationService;

    @InjectMocks
    private OnlineBookShopController controller;

    @BeforeEach
    public void mockitoRule() {
        MockitoAnnotations.initMocks(this);
    }

    List<Books> mockBookList;

    @Test
    public void givenConfirmOrderButton_WhenClickedOnIt_ShouldSendmailToCustomerAndReturnBooleanTrue() {
        Response response = new Response();
        when(orderConfirmationService.confirmOrderAndSendMail()).thenReturn(response);
        Response actualResponse = controller.confirmOrder();
        Assert.assertEquals(response, actualResponse);
    }

    @Test
    public void givenBookStore_WhenClickOnHomePage_ShouldReturnList() throws BookStoreException {
        mockBookList = new ArrayList<>();
        Books books1 = new Books(1, "Chetan Bhagat", "The Girl in Room 105'", "afc", 100, "mre", 10);
        mockBookList.add(books1);
        when(orderConfirmationService.getAllBooks()).thenReturn(mockBookList);
        String expectedAuthor = mockBookList.get(0).getAuthor();
        controller.getList();
        Assert.assertEquals(expectedAuthor, "Chetan Bhagat");
    }

    @Test
    public void givenBookStore_WhenClickOnHomePage_IfRecordListIsNull_ShouldThrowNullException() {
        mockBookList = new ArrayList<>();
        Books books = new Books();
        mockBookList.add(books);
        try {
            BookStoreException expectedException = mock(BookStoreException.class);
            when(orderConfirmationService.getAllBooks()).thenThrow(expectedException);
            controller.getList();
        } catch (BookStoreException e) {
            Assert.assertEquals(BookStoreException.ExceptionType.DATA_NOT_AVAILABLE, e.getType());
        }

    }

    @Test
    public void givenBookStore_WhenClickOnHomePage_ShouldReturnTotalSizeOfRecord() {
        mockBookList = new ArrayList<>();
        Books books1 = new Books(1, "Chetan Bhagat", "The Girl in Room 105'", "afc", 100, "mre", 10);
        Books books2 = new Books();
        mockBookList.add(books2);
        mockBookList.add(books1);
        try {
            when(orderConfirmationService.getAllBooks()).thenReturn(mockBookList);
            controller.getList();
            int size = mockBookList.size();
            Assert.assertEquals(2, size);
        } catch (BookStoreException e) {

        }
    }

    @Test
    public void givenOnUserDetailsPage_whenClickedOnBuyButton_ShouldGetUserDeliveryDetails() {
        Consumer consumer = new Consumer("Karan", "karan24@gmail.com", "kharadi", "202111", "india");
        try {
            when(orderConfirmationService.setDetails(consumer)).thenReturn(consumer.toString());
            String actual = controller.addUserDetails(consumer);
            Assert.assertEquals(consumer.toString(), actual);
        } catch (BookStoreException e) {
        }
    }

    @Test
    public void givenUserDetailsPage_WhenUserEntersWrongDetials_ShouldThrowException() {
        Consumer consumer = new Consumer("Karan", "karan24@gmail.com", "kharadi", "2021", "usa");
        BookStoreException expectedException = mock(BookStoreException.class);
        try {
            when(orderConfirmationService.setDetails(consumer)).thenThrow(expectedException);
            controller.addUserDetails(consumer);
        } catch (BookStoreException e) {
            Assert.assertEquals(BookStoreException.ExceptionType.INVALID_DETAIL, e.getType());
        }
    }
}