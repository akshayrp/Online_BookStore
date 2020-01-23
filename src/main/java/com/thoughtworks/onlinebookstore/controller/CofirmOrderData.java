package com.thoughtworks.onlinebookstore.controller;

import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.dto.ConsumerDto;
import lombok.Data;

import java.util.List;
@Data
public class CofirmOrderData {
    private List<BookDto> bookList;
    private ConsumerDto consumerDto;

    public CofirmOrderData(List<BookDto> bookList, ConsumerDto consumerDto) {
        this.bookList = bookList;
        this.consumerDto = consumerDto;
    }
}
