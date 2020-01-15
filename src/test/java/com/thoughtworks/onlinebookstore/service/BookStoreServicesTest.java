package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Consumer;
import org.junit.Assert;
import org.junit.Test;


public class BookStoreServicesTest {

    @Test
    public void givenOnUserDetailsPage_whenClickedOnBuyButton_ShouldGetUserDeliveryDetails() {
        Consumer consumer = new Consumer("Karan", "karan24@gmail.com", "kharadi", "201901","India");
        OrderConfirmationService orderConfirmationService = new OrderConfirmationService();
        try {
            String s = orderConfirmationService.setDetails(consumer);
            Assert.assertEquals(consumer.toString(),s);
        } catch (BookStoreException e) {
        }
    }

    @Test
    public void givenUserDetails_WhenUserEntersInvalidDetail_ShouldThrowException() {
        Consumer consumer = new Consumer("k", "p", "h", "20","India");
        OrderConfirmationService orderConfirmationService = new OrderConfirmationService();
        try {
           orderConfirmationService.setDetails(consumer);
        } catch (BookStoreException e) {
        }
    }
}