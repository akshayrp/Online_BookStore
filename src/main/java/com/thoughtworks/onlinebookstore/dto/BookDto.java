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

    @NotNull(message = "Book price can't be null")
    private Double price;

    @NotBlank(message = "Book image can't be null")
    private String image;

    @NotBlank(message = "Description can't be null")
    private String description;

    @NotNull(message = "Book price can't be null")
    private Integer quantity;

    public BookDto(String bookName, String authorName, Double price, String description, int quantity, String image) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.image = image;
    }
}
