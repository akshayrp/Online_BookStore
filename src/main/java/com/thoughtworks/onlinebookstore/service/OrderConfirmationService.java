package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.Response.Response;
import com.thoughtworks.onlinebookstore.Response.ResponseHelper;
import com.thoughtworks.onlinebookstore.dto.MailDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.model.Books;
import com.thoughtworks.onlinebookstore.model.Consumer;
import com.thoughtworks.onlinebookstore.model.OrderDetails;
import com.thoughtworks.onlinebookstore.repository.IBookShopRepository;
import com.thoughtworks.onlinebookstore.repository.IConsumerRepository;
import com.thoughtworks.onlinebookstore.repository.IOrderDetailsRepository;
import com.thoughtworks.onlinebookstore.utility.MailData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@PropertySource("classpath:SuccessMessage.properties")

@Service
public class OrderConfirmationService {

    private CountryType countryType;


    @Autowired
    IBookShopRepository bookShopRepository;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private Environment environment;
    @Autowired
    private IOrderDetailsRepository orderDetailsRepository;

    @Autowired
    private IConsumerRepository consumerRepository;
    @Autowired
    MailData mailData;

    private OrderDetails orderDetails;
    private Book book;
    private Optional<Consumer> consumer;

    private String companyEmail = "talltalesbookchembur@gmail.com";
    private String backOfficeEmail = "talltalesbookbackoffice@gmail.com";

    public List<Books> getAllBooks() throws BookStoreException {
        List<Books> booksList = bookShopRepository.findAll();
        if (booksList == null) {
            throw new BookStoreException("data not available", BookStoreException.ExceptionType.DATA_NOT_AVAILABLE);
        }
        return booksList;
    }

    public Book getBookById(int id, int quantity) {
        Books byId = bookShopRepository.findById(id).get();
        this.book = new Book(byId.getId(), byId.getTitle(), byId.getPrice(), quantity);
        return book;
    }

    public String setDetails(Consumer consumer) throws BookStoreException {
        Pattern patternForName = Pattern.compile("^[A-Z]{1}[a-z]{2,}");
        Matcher matchObjName = patternForName.matcher(consumer.getName());

        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9]([-._+]{0,1}[a-zA-Z0-9])*[@]{1}[a-zA-Z0-9]{1,}[.]{1}[a-zA-Z]{2,3}([.]{1}[a-zA-Z]{2,3}){0,1}$");
        Matcher matcherForEmail = emailPattern.matcher(consumer.getEmail());

        Pattern patternForPin = Pattern.compile("^[1-9][0-9]{5}$");
        Matcher matcherForPin = patternForPin.matcher(consumer.getPinCode());

        if (matchObjName.matches() && matcherForEmail.matches() && matcherForPin.matches()) {
            consumerRepository.save(consumer);
            return consumer.toString();
        }
        throw new BookStoreException("invalid details..please check your entered data", BookStoreException
                .ExceptionType.INVALID_DETAIL);
    }

    public Response confirmOrderAndSendMail(long consumerId) {
        consumer = consumerRepository.findById(consumerId);
        MailDto mailDto = new MailDto(consumer.get().getName(), consumer.get().getEmail(), book.getId(), book.getBookName(),
                book.getSelectedQuantity(), this.getTotalPrice());
        mailData.setMailData(mailDto);
        emailSender.send(setDataForCustomer(companyEmail, mailDto.getConsumerEmail(),
                "TallTalesBooks Order Confirmation", mailData.getMailDataForCustomer()));

        emailSender.send(setDataForBackOffice(companyEmail));

        Response response = ResponseHelper.statusResponse(200,
                environment.getProperty("status.mail.MailSentSuccessFully"));
        saveOrderDetails();
        updateQuantity();
        return response;
    }

    private void saveOrderDetails() {

        int totalPrice = this.getTotalPrice();
        orderDetails = new OrderDetails(book.getId(), book.getBookName(), consumer.get().getName(),
                consumer.get().getEmail(), totalPrice);
        System.out.println(orderDetails.toString());

        orderDetailsRepository.save(orderDetails);
    }

    private void updateQuantity() {
        int dbQuantity = bookShopRepository.findById(book.getId()).get().getQuantity();
        int remainingQuantity = dbQuantity - book.getSelectedQuantity();
        Books getBookById = bookShopRepository.findById(book.getId()).get();
        getBookById.setQuantity(remainingQuantity);
        bookShopRepository.save(getBookById);

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

    public int getTotalPrice() {
        if (consumer.get().getCountry().equalsIgnoreCase("India"))
            return (int) ((this.book.getPrice() * book.getSelectedQuantity()) + countryType.INDIA.shippingCharges);
        return (int) ((this.book.getPrice() * book.getSelectedQuantity()) + countryType.OTHER_COUNTRY.shippingCharges);

    }
}


