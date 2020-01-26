package com.thoughtworks.onlinebookstore.model;

import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.dto.ConsumerDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;
@Data
@AllArgsConstructor
public class ConfirmOrderData {
    @Valid
    private List<BookDto> bookList;
    @Valid
    private ConsumerDto consumerDto;
}
