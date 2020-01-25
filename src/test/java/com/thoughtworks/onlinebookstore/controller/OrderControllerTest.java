package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.Response.ResponseHelper;
import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.dto.ConsumerDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.ConfirmOrderData;
import com.thoughtworks.onlinebookstore.service.OrderConfirmationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
public class OrderControllerTest {
    @Mock
    private OrderConfirmationService mockedOrderConfirmationService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenConfirmOrderDetailsWhenDataIsNull_ShouldThrowException() {
        try {
            ArrayList<BookDto> bookDtos = new ArrayList<>();
            BookDto book1 = new BookDto(1, "Two States", "Chetan Bhagat", 100.0, "Image.jpg", "abcd", 1);
            BookDto book2 = new BookDto(2, " States", "Chetan Bhagat", 100.0, "Image.jpg", "abcd", 1);
            bookDtos.add(book1);
            bookDtos.add(book2);
            ConsumerDto consumerDto = new ConsumerDto("INDIA", "jan", "abc1@gmail.com", "xyz", "123456");
            ConfirmOrderData confirmOrderData = new ConfirmOrderData(bookDtos, consumerDto);
            BookStoreException expectedException = new BookStoreException("Failed to send mail", BookStoreException.ExceptionType.MAIL_NOT_SENT);
            Mockito.when(mockedOrderConfirmationService.confirmOrderAndSendMail(consumerDto,bookDtos)).thenThrow(expectedException);
            ResponseEntity responseEntity = orderController.confirmOrder(confirmOrderData);
            Assert.assertEquals(400,responseEntity.getStatusCode().value());
        } catch (BookStoreException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAConfirmOrderDetails_WhenWantToConfirm_ShouldConfirmTHeOrder() {
        try {
            ArrayList<BookDto> bookDtos = new ArrayList<>();
            BookDto book1 = new BookDto(1, "Two States", "Chetan Bhagat", 100.0, "Image.jpg", "abcd", 1);
            BookDto book2 = new BookDto(2, " States", "Chetan Bhagat", 100.0, "Image.jpg", "abcd", 1);
            bookDtos.add(book1);
            bookDtos.add(book2);
            ConsumerDto consumerDto = new ConsumerDto("INDIA", "jan", "abc1@gmail.com", "xyz", "123456");
            ResponseHelper responseHelper=new ResponseHelper(200,"okay");
            Mockito.when(mockedOrderConfirmationService.confirmOrderAndSendMail(consumerDto,bookDtos)).thenReturn(responseHelper);
            ConfirmOrderData confirmOrderData = new ConfirmOrderData(bookDtos, consumerDto);
            ResponseEntity responseEntity = orderController.confirmOrder(confirmOrderData);
            Assert.assertEquals(200,responseEntity.getStatusCode().value());
        } catch (BookStoreException e) {
            e.printStackTrace();
        }



    }
}