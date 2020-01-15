package com.thoughtworks.onlinebookstore.dto;


public class BookDto {
    private Long bookId;
    private String bookName;
    private String authorName;
    private Double price;
    private String description;
    private Long quantity;

    public BookDto() {
    }

    public BookDto(Long bookId, String bookName, String authorName, Double price, String description, Long quantity) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public Long getBookId() {
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

    public Long getQuantity() {
        return quantity;
    }
}
