package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Consumer;
import com.thoughtworks.onlinebookstore.service.OrderConfirmationService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OnlineBookShopControllerTest {

    @Mock
    private OrderConfirmationService mockedOrderConfirmationService;

    @InjectMocks
    private OnlineBookShopController myController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenOnUserDetailsPage_whenClickedOnBuyButton_ShouldGetUserDeliveryDetails() {
        Consumer consumer = new Consumer("Karan", "karan24@gmail.com", "kharadi", "202111","India");
        try {
            when(mockedOrderConfirmationService.setDetails(consumer)).thenReturn(consumer.toString());
            String actual = myController.addUserDetails(consumer);
            Assert.assertEquals(consumer.toString(), actual);
        } catch (BookStoreException e) {
        }
    }

    @Test
    public void givenUserDetailsPage_WhenUserEntersWrongDetials_ShouldThrowException() {
        Consumer consumer = new Consumer("Karan", "karan24@gmail.com", "kharadi", "2021","India");
        BookStoreException expectedException = mock(BookStoreException.class);
        try {
            when(mockedOrderConfirmationService.setDetails(consumer)).thenThrow(expectedException);
            myController.addUserDetails(consumer);
        } catch (BookStoreException e) {
            Assert.assertEquals(BookStoreException.ExceptionType.INVALID_DETAIL, e.type);
        }
    }
}