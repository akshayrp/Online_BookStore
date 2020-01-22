package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.Response.ResponseHelper;
import com.thoughtworks.onlinebookstore.dto.ConsumerDto;
import com.thoughtworks.onlinebookstore.dto.MailDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.model.Consumer;
import com.thoughtworks.onlinebookstore.model.OrderDetails;
import com.thoughtworks.onlinebookstore.repository.IConsumerRepository;
import com.thoughtworks.onlinebookstore.repository.IOrderDetailsRepository;
import com.thoughtworks.onlinebookstore.utility.MailData;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@PropertySource("classpath:SuccessMessage.properties")

@Service
public class OrderConfirmationService {

    private CountryType countryType;

    @Autowired
    private BookStoreServices bookStoreServices;

    @Autowired
    private IOrderDetailsRepository orderDetailsRepository;

    @Autowired
    private IConsumerRepository consumerRepository;

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


    public Consumer setDetails(ConsumerDto consumerDto) {
        Consumer consumer = mapper.map(consumerDto, Consumer.class);
        consumerRepository.save(consumer);
        String name = consumer.getName();
        return consumerRepository.findConsumerByName(name);
    }

    private void saveOrderDetails(List<Book> bookList, ConsumerDto consumer) {
        OrderDetails orderDetails = new OrderDetails();
        int orderNumber = orderDetailsRepository.findTopByOrderByOrderIdDesc().getOrderNumber();
        orderNumber = orderNumber + 1;
        for (Book book : bookList) {
            orderDetails = new OrderDetails(orderNumber, book.getBookId(), book.getBookName(), consumer.getName(),
                    consumer.getEmail(), book.getPrice() * book.getQuantity());
            orderDetailsRepository.save(orderDetails);
        }
    }

    public Book getPurchasingBook(int id, int quantity) {
        Book bookById = bookStoreServices.getBookById(id);
        this.book = new Book(bookById.getBookId(), bookById.getBookName(), bookById.getPrice(), quantity);
        return bookById;
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

    public ResponseHelper confirmOrderAndSendMail(ConsumerDto consumer, List<Book> bookList) {
        MailDto mailDto = new MailDto(consumer.getName(), consumer.getEmail());
        mailData.setMailData(mailDto, bookList);
        try {
            bookStoreServices.updateQuantity(bookList);
        } catch (BookStoreException e) {
            e.getMessage();
        }
        emailSender.send(setDataForBackOffice(companyEmail));
        emailSender.send(setDataForCustomer(companyEmail, mailDto.getConsumerEmail(),
                "TallTalesBooks Order Confirmation", mailData.getMailDataForCustomer()));
        saveOrderDetails(bookList, consumer);
        return new ResponseHelper(200, environment.getProperty("status.mail.MailSentSuccessFully"));
    }
}



