package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.dto.ConsumerDto;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderConfirmationServiceTest {
    List<Book> booksList;
    List<BookDto> bookDtoList;

    @Mock
    BookStoreServices mockedBookStoreServices;

    @Mock
    private MailData mockedMailData;

    @Mock
    private JavaMailSender mockedEmailSender;

    @Mock
    private Environment mockedEnvironment;

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

}
