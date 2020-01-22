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
    private int orderNumber;
    private int bookId;
    private String bookName;
    private String consumerName;
    private String consumerEmail;
    private double totalPrice;

    public OrderDetails() {
    }

    public OrderDetails(int bookId, String bookName, String consumerName, String consumerEmail, double totalPrice) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.consumerName = consumerName;
        this.consumerEmail = consumerEmail;
        this.totalPrice = totalPrice;
    }

    public OrderDetails(int orderNumber, int bookId, String bookName, String name, String email, double totalPrice) {
        this.orderNumber= orderNumber;
        this.bookId = bookId;
        this.bookName = bookName;
        this.consumerName = name;
        this.consumerEmail = email;
        this.totalPrice = totalPrice;
    }
}
