package com.thoughtworks.onlinebookstore.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class BookDto {

    @NotBlank(message = "BookName can't be null")
    private String bookName;

    @NotBlank(message = "AuthorName can't be null")
    private String authorName;

    @NotBlank(message = "BookEdition can't be null")
    private String bookEdition;

    @NotNull( message = "Book price can't be null")
    private Double price;

    @NotBlank(message = "Description can't be null")
    private String description;

    @NotNull(message = "Book price can't be null")
    private Integer quantity;

    public BookDto(String bookName, String authorName, String bookEdition, Double price, String description, int quantity) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookEdition = bookEdition;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }
}
