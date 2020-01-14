package com.thoughtworks.onlinebookstore.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class OnlineBookShopControllerTest {
    @Test
    public void givenOnUserDetailsPage_whenClickedOnBuyButton_ShouldGetUserDeliveryDetails() {
        OnlineBookShopController myController = new OnlineBookShopController();
        String name = myController.addUserDetails("sargam");
        Assert.assertEquals("sargam",name);
    }

}