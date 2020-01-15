package com.thoughtworks.onlinebookstore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookDto {

    private Long bookId;
    private String bookName;
    private String authorName;
    private Double price;
    private String description;
    private Long quantity;

    public BookDto(Long bookId,String bookName, String authorName, Double price, String description, Long quantity) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }
}
