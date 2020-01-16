package com.thoughtworks.onlinebookstore.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Book {

    public Book() { }

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String bookName;
    private String authorName;
    private String bookEdition;
    private Double price;
    private String description;
    private int selectedQuantity;


    public Book(int id, String title, double price, int quantity) {
    }
}
