package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.Response.Response;
import com.thoughtworks.onlinebookstore.model.Consumer;
import com.thoughtworks.onlinebookstore.utility.MailData;
import com.thoughtworks.onlinebookstore.Response.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@PropertySource("classpath:SuccessMessage.properties")
@Service
public class OrderConfirmationService {
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private Environment environment;

    private String companyEmail = "talltalesbookchembur@gmail.com";
    private String backOfficeEmail = "talltalesbookbackoffice@gmail.com";

    public Response confirmOrderAndSendMail() {
        Consumer consumer = new Consumer();
        emailSender.send(setDataForCustomer(companyEmail,consumer.getEmail(),
                "TallTalesBooks Order Confirmation", MailData.getMailDataForCustomer()));

        emailSender.send(setDataForBackOffice(companyEmail));

        Response response = ResponseHelper.statusResponse(200,
                        environment.getProperty("status.mail.MailSentSuccessFully"));
        return response;
    }

    private SimpleMailMessage setDataForCustomer(String from, String to, String subject, String text) {
        SimpleMailMessage userMessage = new SimpleMailMessage();
        userMessage.setFrom(from);
        userMessage.setTo(to);
        userMessage.setSubject(subject);
        userMessage.setText(text);
        return userMessage;
    }

    private SimpleMailMessage setDataForBackOffice(String from) {
        SimpleMailMessage backOfficeMessage = new SimpleMailMessage();
        backOfficeMessage.setFrom(from);
        backOfficeMessage.setTo(backOfficeEmail);
        backOfficeMessage.setSubject("Order Received");
        backOfficeMessage.setText(MailData.getMailDataForBackOffice());
        return backOfficeMessage;
    }
}