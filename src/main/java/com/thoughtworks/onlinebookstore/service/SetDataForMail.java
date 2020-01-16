package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.utility.MailData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class SetDataForMail {
    @Autowired
    private MailData mailData;

    private String companyEmail = "talltalesbookchembur@gmail.com";
    private String backOfficeEmail = "talltalesbookbackoffice@gmail.com";

    public SimpleMailMessage setDataForCustomer(String from, String to, String subject, String text) {
        SimpleMailMessage userMessage = new SimpleMailMessage();
        userMessage.setFrom(from);
        userMessage.setTo(to);
        userMessage.setSubject(subject);
        userMessage.setText(text);
        return userMessage;
    }

    public SimpleMailMessage setDataForBackOffice() {
        SimpleMailMessage backOfficeMessage = new SimpleMailMessage();
        backOfficeMessage.setFrom(companyEmail);
        backOfficeMessage.setTo(backOfficeEmail);
        backOfficeMessage.setSubject("Order Received");
        backOfficeMessage.setText(mailData.getMailDataForBackOffice());
        return backOfficeMessage;
    }
}
