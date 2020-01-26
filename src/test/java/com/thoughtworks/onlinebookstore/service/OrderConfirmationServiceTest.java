package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.dto.ConsumerDto;
import com.thoughtworks.onlinebookstore.dto.MailDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.model.OrderDetails;
import com.thoughtworks.onlinebookstore.repository.IBookStoreRepository;
import com.thoughtworks.onlinebookstore.repository.IOrderDetailsRepository;
import com.thoughtworks.onlinebookstore.utility.MailData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderConfirmationServiceTest {
    List<Book> booksList;
    List<BookDto> bookDtoList;
@Mock
private BookStoreServices bookStoreServices;
    @Mock
    private MailData mockedMailData;

    @Mock
    private JavaMailSender mockedEmailSender;

    @Mock
    private Environment mockedEnvironment;

    @Mock
    private IOrderDetailsRepository iOrderDetailsRepository;

    @InjectMocks
    OrderConfirmationService orderConfirmationService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenBookStore_WhenPurchasedMultipleBooks_ShouldReturnString() {
        booksList = new ArrayList<>();
        Book books1 = new Book(1, "The Girl in 105", "abc", 200.1,
                "author", "abcd.jpg", 3);
        Book books2 = new Book(2, "Da Vinci Code" +
                "", "xyz", 199.0, "author1", "abcd1.png", 3);
        booksList.add(books1);
        booksList.add(books2);
        MailDto mailDto = new MailDto("AAAAAAAAAAAAAA", "AAAAASSSSS", 1, "SSSSSSSSSSSSSSSSSs", 5, 100.0, 2);
        MailData mailData = new MailData();
        mailData.setMailData(mailDto, booksList);
    }

    @Test
    public void givenCustomerEmail_WhenConfirmedTheOrder_ShouldReturnItsOrderId() {
        try {
            ArrayList<OrderDetails> detailsArrayList = new ArrayList<>();
            OrderDetails orderDetails = new OrderDetails(1, 1, 2, "The pilot", "Pan", "abc1@gmail.com", 10.0);
            OrderDetails orderDetails2 = new OrderDetails(2, 5, 2, "The pilot", "Pan", "abc1@gmail.com", 10.0);
            OrderDetails orderDetails3 = new OrderDetails(3, 9, 1, "The Prey", "Pan", "abc1@gmail.com", 10.0);
            detailsArrayList.add(orderDetails);
            detailsArrayList.add(orderDetails2);
            detailsArrayList.add(orderDetails3);
            Mockito.when(iOrderDetailsRepository.findTopByConsumerEmailOrderByOrderIdDesc("abc1@gmail.com")).thenReturn(orderDetails3);
            int orderId = 0;
            orderId = orderConfirmationService.getOrderId("abc1@gmail.com");
            Assert.assertEquals(3,orderId);
        } catch (BookStoreException e) {
        }
    }

    @Test
    public void givenNonExistCustomerEmail_WhenTryToGetOrderId_ShouldThrowException() {
        try {
            ArrayList<OrderDetails> detailsArrayList = new ArrayList<>();
            OrderDetails orderDetails = new OrderDetails(1, 1, 2, "The pilot", "Pan", "abc1@gmail.com", 10.0);
            OrderDetails orderDetails2 = new OrderDetails(2, 5, 2, "The pilot", "Pan", "abc1@gmail.com", 10.0);
            OrderDetails orderDetails3 = new OrderDetails(3, 9, 1, "The Prey", "Pan", "abc1@gmail.com", 10.0);
            detailsArrayList.add(orderDetails);
            detailsArrayList.add(orderDetails2);
            detailsArrayList.add(orderDetails3);
            BookStoreException expectedException = new BookStoreException("No such email", BookStoreException.ExceptionType.DATA_NOT_AVAILABLE);
            Mockito.when(iOrderDetailsRepository.findTopByConsumerEmailOrderByOrderIdDesc("thisIsNotACustomerId1@gmail.com")).thenReturn(null);
            orderConfirmationService.getOrderId("thisIsNotACustomerId1@gmail.com");
        } catch (BookStoreException e) {
            Assert.assertEquals(BookStoreException.ExceptionType.DATA_NOT_AVAILABLE,e.getType());
        }
    }

}
