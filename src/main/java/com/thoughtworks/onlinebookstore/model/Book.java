package com.thoughtworks.onlinebookstore.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long bookId;
    private String bookName;
    private String authorName;
    private String bookEdition;
    private Double price;
    private String description;
    private Long quantity;
}
