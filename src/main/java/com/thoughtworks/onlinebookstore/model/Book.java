package com.thoughtworks.onlinebookstore.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private int bookId;
    private String bookName;
    private String authorName;
    private Double price;
    private String image;
    private String description;
    private int quantity;

}
