package com.thoughtworks.onlinebookstore.dto;

import org.springframework.stereotype.Component;

@Component
public class MailDto {
    private String consumerName;
    private String consumerEmail;
    private int bookId;
    private String bookTitle;
    private int bookQuantity;
    private double totalPrice;

    public MailDto() {
    }

    public MailDto(String consumerName, String consumerEmail, int bookId, String bookTitle, int bookQuantity, double totalPrice) {
        this.consumerName = consumerName;
        this.consumerEmail = consumerEmail;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookQuantity = bookQuantity;
        this.totalPrice = totalPrice;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getConsumerEmail() {
        return consumerEmail;
    }
}
