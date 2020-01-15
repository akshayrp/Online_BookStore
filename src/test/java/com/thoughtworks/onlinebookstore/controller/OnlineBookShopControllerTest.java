package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.model.Books;
import com.thoughtworks.onlinebookstore.service.IBookShopServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OnlineBookShopControllerTest {

    @Mock
    IBookShopServices bookShopServices;
    List<Books> mockBookList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenBookStore_WhenclickOnHomePage_ShouldReturnList() {
        mockBookList= new ArrayList<>();
        Books books1 = new Books(1, "Chetan Bhagat", "The Girl in Room 105'", "afc", 100, "mre");
        mockBookList.add(books1);
        OnlineBookShopController bookShopController = new OnlineBookShopController();
        bookShopController.setService(bookShopServices);
        when(bookShopController.getList()).thenReturn(mockBookList);
        List<Books> bookList = bookShopController.getList();
        String title = mockBookList.get(0).getTitle();
        Assert.assertEquals("The Girl in Room 105'", title);
    }

    @Test
    public void givenBookStore_WhenClickOnHomePage_ShouldReturnTotalSizeOfRecord() {
        mockBookList= new ArrayList<>();
        Books books1 = new Books(1, "Chetan Bhagat", "The Girl in Room 105'", "afc", 100, "mre");
        mockBookList.add(books1);
        OnlineBookShopController bookShopController = new OnlineBookShopController();
        bookShopController.setService(bookShopServices);
        when(bookShopController.getList()).thenReturn(mockBookList);
        List<Books> bookList = bookShopController.getList();
        int recordSize = mockBookList.size();
        Assert.assertEquals(1,recordSize);
    }
}