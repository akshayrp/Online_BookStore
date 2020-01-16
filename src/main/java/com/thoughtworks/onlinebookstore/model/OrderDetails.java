package com.thoughtworks.onlinebookstore.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Component
@Entity
@Data
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private int bookId;
    private String bookName;
    private String name;
    private String email;
    private String consumerName;
    private String consumerEmail;
    private double totalPrice;

    public OrderDetails() {
    }

    public OrderDetails(int bookId, String bookName, String name, String email, double totalPrice) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.name = name;
        this.email = email;
        this.totalPrice = totalPrice;
    }
}
