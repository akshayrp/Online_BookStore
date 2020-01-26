package com.thoughtworks.onlinebookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class BookDto {

    @Id
    Integer bookId;
    @NotBlank(message = "BookName can't be null")
    private String bookName;

    @NotBlank(message = "AuthorName can't be null")
    private String authorName;

    @NotNull(message = "Book price can't be null")
    @DecimalMin("1")
    private Double price;

    @NotBlank(message = "image path can't be null")
    private String image;

    @NotBlank(message = "Description can't be null")
    private String description;

    @NotNull(message = "Book price can't be null")
    @DecimalMin("1")
    private Integer quantity;

    public BookDto(Integer bookId, String bookName, String authorName, Double price, String image, String description, Integer quantity){

        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.price = price;
        this.image = image;
        this.description = description;
        this.quantity = quantity;
    }
}
