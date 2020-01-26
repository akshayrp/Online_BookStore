package com.thoughtworks.onlinebookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
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


}
