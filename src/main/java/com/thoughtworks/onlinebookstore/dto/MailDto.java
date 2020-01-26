package com.thoughtworks.onlinebookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {
    private String consumerName;
    private String consumerEmail;
    private int bookId;
    private String bookTitle;
    private int bookQuantity;
    private double totalPrice;
    private long consumerId;

    public MailDto(String name, String email) {
        this.consumerName = name;
        this.consumerEmail = email;
    }

}
