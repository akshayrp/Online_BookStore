package com.thoughtworks.onlinebookstore.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

public class BookShopServiceImpTest {

    @InjectMocks
    private BookStoreServices bookShopServicesImp = new BookStoreServices();

    @Test
    void givenBookData_WhenTryToAddNull_ShouldThrowException() {
        try {
            bookShopServicesImp.addBook(null);
        } catch (Exception e) {
            Assert.assertEquals(null, e.getMessage());
        }
    }
}
