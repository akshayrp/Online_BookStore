package com.thoughtworks.onlinebookstore.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Component
@Entity
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    private int bookId;
    private String bookName;
    private String consumerName;
    private String consumerEmail;
    private double totalPrice;

    public OrderDetails() {
    }

    public OrderDetails(int bookId,String bookName,String consumerName,String consumerEmail,double totalPrice) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.consumerName = consumerName;
        this.consumerEmail = consumerEmail;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public String getConsumerEmail() {
        return consumerEmail;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
