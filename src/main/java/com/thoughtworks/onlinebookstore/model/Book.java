package com.thoughtworks.onlinebookstore.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Book {

    public Book() { }

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private int bookId;
    private String bookName;
    private String authorName;
    private String bookEdition;
    private Double price;
    private String description;
    private int quantity;


    public Book(int bookId, String title, double price, int quantity) {
        this.bookId = bookId;
        this.bookName = title;
        this.price = price;
        this.quantity = quantity;
    }
}
