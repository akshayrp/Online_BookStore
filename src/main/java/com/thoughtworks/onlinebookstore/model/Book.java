package com.thoughtworks.onlinebookstore.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Book {

    public Book() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;
    private String bookName;
    private String authorName;
    private Double price;
    private String image;
    private String description;
    private int quantity;


    public Book(String title, double price, int quantity, String authorName, String image, String description) {
        this.authorName = authorName;
        this.bookName = title;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
    }

    public Book(int bookId, String title, double price, int quantity) {
        this.bookId = bookId;
        this.bookName = title;
        this.price = price;
        this.quantity = quantity;
    }

}
