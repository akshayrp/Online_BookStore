package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.Response.ResponseHelper;
import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.dto.ConsumerDto;
import com.thoughtworks.onlinebookstore.dto.MailDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.model.Consumer;
import com.thoughtworks.onlinebookstore.model.OrderDetails;
import com.thoughtworks.onlinebookstore.repository.IOrderDetailsRepository;
import com.thoughtworks.onlinebookstore.utility.MailData;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@PropertySource("classpath:SuccessMessage.properties")

@Service
public class OrderConfirmationService {

    @Autowired
    private BookStoreServices bookStoreServices;

    @Autowired
    private IOrderDetailsRepository orderDetailsRepository;

    @Autowired
    private MailData mailData;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private Environment environment;

    @Autowired
    private ModelMapper mapper;

    private Book book;
    private Optional<Consumer> consumer;

    private String companyEmail = "talltalesbookchembur@gmail.com";
    private String backOfficeEmail = "talltalesbookbackoffice@gmail.com";

    private void saveOrderDetails(List<Book> bookList, ConsumerDto consumer) {
        OrderDetails orderDetails = new OrderDetails();
        int orderNumber = orderDetailsRepository.findTopByOrderByOrderIdDesc().getOrderNumber();
        orderNumber = orderNumber + 1;
        for (Book book : bookList) {
            orderDetails = new OrderDetails(orderNumber,orderDetails.getOrderNumber(), book.getBookId(), book.getBookName(), consumer.getName(),
                    consumer.getEmail(), book.getPrice() * book.getQuantity());
            orderDetailsRepository.save(orderDetails);
        }
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
        backOfficeMessage.setText(mailData.getMailDataForBackOffice());
        return backOfficeMessage;
    }

    public ResponseHelper confirmOrderAndSendMail(ConsumerDto consumer, List<BookDto> bookDtoList) throws BookStoreException {
        try {
            MailDto mailDto = new MailDto(consumer.getName(), consumer.getEmail());
            List<Book> bookList = new ArrayList<>();
            bookDtoList.stream().forEach(bookDto -> bookList.add(mapper.map(bookDto,Book.class)));
            mailData.setMailData(mailDto, bookList);
            bookStoreServices.updateQuantity(bookList);
            emailSender.send(setDataForBackOffice(companyEmail));
            emailSender.send(setDataForCustomer(companyEmail, mailDto.getConsumerEmail(),
                    "TallTalesBooks Order Confirmation", mailData.getMailDataForCustomer()));
            saveOrderDetails(bookList, consumer);
        } catch (BookStoreException e) {
            throw new BookStoreException("cannot send mail..",BookStoreException.ExceptionType.MAIL_NOT_SENT);
        }
        return new ResponseHelper(200, environment.getProperty("status.mail.MailSentSuccessFully"));
    }

    public Integer getOrderId(String customerEmail) {
       return orderDetailsRepository.findTopByConsumerEmailOrderByOrderIdDesc(customerEmail).getOrderId();
    }
}



