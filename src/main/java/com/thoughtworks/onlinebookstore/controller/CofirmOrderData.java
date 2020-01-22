package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.dto.ConsumerDto;
import com.thoughtworks.onlinebookstore.dto.MailDto;
import com.thoughtworks.onlinebookstore.model.Book;
import lombok.Data;

import java.util.List;
@Data
public class CofirmOrderData {
    private List<Book> bookList;
    private ConsumerDto consumerDto;

    public CofirmOrderData(List<Book> bookList, ConsumerDto consumerDto) {
        this.bookList = bookList;
        this.consumerDto = consumerDto;
    }
}
