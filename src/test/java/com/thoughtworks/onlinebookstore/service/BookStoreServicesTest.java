package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Consumer;
import org.junit.Assert;
import org.junit.Test;


public class BookStoreServicesTest {

    @Test
    public void givenOnUserDetailsPage_whenClickedOnBuyButton_ShouldGetUserDeliveryDetails() {
        Consumer consumer = new Consumer("Karan", "karan24@gmail.com", "kharadi", "201901","India");
        BookStoreServices bookStoreServices = new BookStoreServices();
        try {
            String s = bookStoreServices.setDetails(consumer);
            Assert.assertEquals(consumer.toString(),s);
        } catch (BookStoreException e) {
        }
    }

    @Test
    public void givenUserDetails_WhenUserEntersInvalidDetail_ShouldThrowException() {
        Consumer consumer = new Consumer("k", "p", "h", "20","India");
        BookStoreServices bookStoreServices = new BookStoreServices();
        try {
           bookStoreServices.setDetails(consumer);
        } catch (BookStoreException e) {
        }
    }
}