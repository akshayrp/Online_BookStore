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
}
