package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Consumer;
import org.junit.Assert;
import org.junit.Test;

public class OrderConfirmationWithoutMockTest {

    OrderConfirmationService orderConfirmationService = new OrderConfirmationService();

    @Test
    public void givenOnUserDetailsPage_whenClickedOnBuyButton_ShouldGetUserDeliveryDetails() {
        Consumer consumer = new Consumer("Karan", "karan24@gmail.com", "kharadi", "201901", CountryType.INDIA);
        try {
            String s = orderConfirmationService.setDetails(consumer);
            Assert.assertEquals(consumer.toString(), s);
        } catch (BookStoreException e) {
        }
    }

    @Test
    public void givenUserDetails_WhenUserEntersInvalidDetail_ShouldThrowException() {
        Consumer consumer = new Consumer("k", "p", "h", "20", CountryType.INDIA);
        try {
            orderConfirmationService.setDetails(consumer);
        } catch (BookStoreException e) {
        }
    }
}