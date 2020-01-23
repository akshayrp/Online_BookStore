package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.dto.MailDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.repository.IBookStoreRepository;
import com.thoughtworks.onlinebookstore.utility.MailData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderConfirmationServiceTest {

    List<Book> booksList;
    List<Book> booksList1;

    @Mock
    private IBookStoreRepository mockedBookShopRepository;

    @InjectMocks
    private BookStoreServices storeServices;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenBookStore_WhenClickOnHomePage_ShouldReturnList() throws BookStoreException {
        booksList = new ArrayList<>();
        booksList1 = new ArrayList<>();

        Book books1 = new Book("pooja",100,10,"author","abcd","hello");
        booksList.add(books1);
        when(mockedBookShopRepository.findAll()).thenReturn(booksList);
       booksList1 = storeServices.getAllBooks();
        Assert.assertEquals("author", booksList1.get(0).getAuthorName());
    }


    @Test
    public void givenBookStore_WhenClickOnHomePage_ShouldReturnTotalSizeOfRecord() throws BookStoreException {
        booksList = new ArrayList<>();
        Book books1 = new Book("pooja",100,10,"author","abcd","hello");
        Book books2 = new Book("pooja1",100,10,"author1","abcd1","hello1");
        booksList.add(books1);
        booksList.add(books2);
        when(mockedBookShopRepository.findAll()).thenReturn(booksList);
        storeServices.getAllBooks();
        int size = booksList.size();
        Assert.assertEquals(2, size);
    }

    @Test
    public void BookDetailVarification() {

    }

    @Test
    public void givenBookStore_WhenPurchasedMultipleBooks_ShouldReturnString() {
        booksList = new ArrayList<>();
        Book books1 = new Book("The Girl in 105",200,10,"author","abcd.jpg","hello");
        Book books2 = new Book("Da Vinci Code" +
                "",100,10,"author1","abcd1.png","hello1");
        booksList.add(books1);
        booksList.add(books2);
        MailDto mailDto = new MailDto("AAAAAAAAAAAAAA", "AAAAASSSSS", 1, "SSSSSSSSSSSSSSSSSs", 5, 100.0, 2);
        MailData mailData = new MailData();
        mailData.setMailData(mailDto,booksList);
        System.out.println(mailData.getMailDataForBackOffice());
    }
}