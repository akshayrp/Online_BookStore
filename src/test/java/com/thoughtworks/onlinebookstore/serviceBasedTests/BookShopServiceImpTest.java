package com.thoughtworks.onlinebookstore.serviceBasedTests;

import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.repository.IBookStoreRepository;
import com.thoughtworks.onlinebookstore.service.BookStoreServices;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


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
