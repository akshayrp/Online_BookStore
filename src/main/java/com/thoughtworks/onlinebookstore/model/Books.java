package com.thoughtworks.onlinebookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String author;
    String title;
    String image;
    int price;
    String description;
    int availableQuantity;

    public Books() { }

    public Books(int id, String author, String title, String image, int price, String description, int availableQuantity) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.image = image;
        this.price = price;
        this.description = description;
        this.availableQuantity =availableQuantity;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
