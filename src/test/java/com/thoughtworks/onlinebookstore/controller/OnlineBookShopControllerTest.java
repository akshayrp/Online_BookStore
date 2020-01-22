package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.Response.ResponseHelper;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
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
    private OrderConfirmationService orderConfirmationService;

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
    public void givenConfirmOrderButton_WhenClickedOnIt_ShouldSendmailToCustomerAndReturnBooleanTrue() {
        ResponseHelper response = new ResponseHelper(200, environment.getProperty("status.mail.MailSentSuccessFully"));
        when(orderConfirmationService.confirmOrderAndSendMail(any())).thenReturn(response);
        String actualResponse = controller.confirmOrder(1l);
        Assert.assertEquals(response.toString(), actualResponse);
    }

   /* @Test
    public void givenBookStore_WhenClickOnHomePage_ShouldReturnList() throws BookStoreException {
        mockBookList = new ArrayList<>();
        Book books1 = new Book(1, "Chetan Bhagat", "The Girl in Room 105'", "afc", 100.0, "mre", 10);
        mockBookList.add(books1);
        when(orderConfirmationService.getAllBooks()).thenReturn(mockBookList);
        String expectedAuthor = mockBookList.get(0).getAuthorName();
        controller.getList();
        Assert.assertEquals(expectedAuthor, "Chetan Bhagat");
    }*/

    @Test
    public void givenBookStore_WhenClickOnHomePage_IfRecordListIsNull_ShouldThrowNullException() {
        mockBookList = new ArrayList<>();
        Book book = new Book();
        mockBookList.add(book);
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
        Book books1 = new Book( "The Girl in Room 105'",100.0, 10,"Chetan Bhagat" , "image.src", "asdf");
        Book books2 = new Book();
        mockBookList.add(books2);
        mockBookList.add(books1);
        try {
            when(orderConfirmationService.getAllBooks()).thenReturn(mockBookList);
            controller.getList();
            int size = mockBookList.size();
            Assert.assertEquals(2, size);
        } catch (BookStoreException e) { }
    }

   /* @Test
    public void givenOnUserDetailsPage_whenClickedOnBuyButton_ShouldGetUserDeliveryDetails() {
        Consumer consumer = new Consumer("Karan", "karan24@gmail.com", "kharadi", "202111", "india");
        when(orderConfirmationService.setDetails(consumer)).thenReturn(consumer);
        Consumer consumer1 = controller.addUserDetails(consumer);
        Assert.assertEquals(consumer.toString(),consumer1.toString() );
    }

    @Test
    public void givenUserDetailsPage_WhenUserEntersWrongDetials_ShouldThrowException() {
        Consumer consumer = new Consumer("Karan", "karan24@gmail.com", "kharadi", "2021", "usa");
        BookStoreException expectedException = mock(BookStoreException.class);
        when(orderConfirmationService.setDetails(consumer)).thenThrow(expectedException);
        controller.addUserDetails(consumer);
    }*/
}