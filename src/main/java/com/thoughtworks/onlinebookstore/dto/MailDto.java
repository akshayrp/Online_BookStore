package com.thoughtworks.onlinebookstore.dto;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MailDto {
    private String consumerName;
    private String consumerEmail;
    private int bookId;
    private String bookTitle;
    private int bookQuantity;
    private double totalPrice;
    private long consumerId;

    public MailDto() {
    }


    public MailDto(String name, String email, int bookId, String bookName, int quantity, double totalPrice, long consumerId) {
        this.consumerId = consumerId;
        this.consumerName = name;
        this.bookId = bookId;
        this.consumerEmail = email;
        this.bookTitle = bookName;
        this.bookQuantity = quantity;
        this.totalPrice = totalPrice;
    }
}
