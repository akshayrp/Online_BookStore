package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.Response.Response;
import com.thoughtworks.onlinebookstore.service.OrderConfirmationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OnlineBookShopControllerTest {

    @Mock
    private OrderConfirmationService orderConfirmationService;

    @InjectMocks
    private OnlineBookShopController controller;


    @BeforeEach
    public void mockitoRule() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenConfirmOrderButton_WhenClickedOnIt_ShouldSendmailToCustomerAndReturnBooleanTrue() {
        Response response = new Response();
        when(orderConfirmationService.confirmOrderAndSendMail()).thenReturn(response);
        Response actualResponse = controller.confirmOrder();
        Assert.assertEquals(response, actualResponse);
    }
}