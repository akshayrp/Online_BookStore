package com.thoughtworks.onlinebookstore.model;

import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.dto.ConsumerDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class ConfirmOrderData {
    private List<BookDto> bookList;
    private ConsumerDto consumerDto;
}
