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

@RunWith(MockitoJUnitRunner.class)
public class BookShopServiceImpTest {

    @Mock
    private IBookStoreRepository bookShopRepository;
    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private BookStoreServices bookShopServicesImp = new BookStoreServices();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void givenBook_WhenAddedToDB_ShouldReturnTrue_Mock() {
        try {
            BookDto bookDto = new BookDto("PrinceBio", "Prince Singh", "1.0",150D, "Biography Prince Singh", 10L);
            Book book = mock(Book.class);
            when(bookShopRepository.save(any())).thenReturn(new Book());
            when(mapper.map(bookDto, Book.class)).thenReturn(book);
            boolean response = bookShopServicesImp.addBook(bookDto);
            Assert.assertEquals(true, response);
        } catch (BookStoreException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenBookData_WhenTryToAddNull_ShouldThrowException() {
        try {
            bookShopServicesImp.addBook(null);
        } catch (BookStoreException e) {
            Assert.assertEquals(BookStoreException.ExceptionType.CANT_ADD_NULL_DATA, e.getType());
        }
    }
}
