package com.thoughtworks.onlinebookstore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


@Getter
@NoArgsConstructor
public class BookDto {


    @NotBlank(message = "BookName can't be null")
    private String bookName;

    @NotBlank(message = "AuthorName can't be null")
    private String authorName;

    @NotBlank(message = "BookEdition can't be null")
    private String bookEdition;

//    @NotEmpty(message = "Book price can't be null")
    private Double price;

    @NotBlank(message = "Description can't be null")
    private String description;

//    @NotEmpty(message = "Book price can't be null")
    private Long quantity;

    public BookDto(String bookName, String authorName, String bookEdition, Double price, String description, Long quantity) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookEdition = bookEdition;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }
}
