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

    public Book(int id, String bookName, Double price, int selectedQuantity) {
        this.id = id;
        this.bookName = bookName;
        this.price = price;
        this.selectedQuantity = selectedQuantity;
    }

    public int getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public Double getPrice() {
        return price;
    }

    public int getSelectedQuantity() {
        return selectedQuantity;
    }
}
