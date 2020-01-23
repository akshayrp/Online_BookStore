package com.thoughtworks.onlinebookstore.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class BookDto {

    @NotBlank(message = "BookName can't be null")
    private String bookName;

    @NotBlank(message = "AuthorName can't be null")
    private String authorName;

    @NotNull(message = "Book price can't be null")
    @DecimalMin("1")
    private Double price;

    @Pattern(regexp = "(^?[A-Za-z0-9])+.(?:jpg|gif|png)$",message = "Invalid image type")
    private String image;

    @NotBlank(message = "Description can't be null")
    private String description;

    @NotNull(message = "Book price can't be null")
    @DecimalMin("1")
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
