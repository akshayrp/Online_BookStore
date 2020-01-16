package com.thoughtworks.onlinebookstore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;


public class BookDto {

    public BookDto() { }

    private int bookId;

    @NotBlank(message = "BookName can't be null")
    private String bookName;

    @NotBlank(message = "AuthorName can't be null")
    private String authorName;

    @NotBlank(message = "BookEdition can't be null")
    private String bookEdition;

    @NotBlank(message = "Book price can't be null")
    private Double price;

    @NotBlank(message = "Description can't be null")
    private String description;

    @NotBlank(message = "Book price can't be null")
    private int quantity;

    public BookDto(int bookId,String bookName, String authorName, String bookEdition, Double price, String description, int quantity) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookEdition = bookEdition;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }
}
