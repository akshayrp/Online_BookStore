package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.Response.ResponseHelper;
import com.thoughtworks.onlinebookstore.dto.MailDto;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.model.Consumer;
import com.thoughtworks.onlinebookstore.model.OrderDetails;
import com.thoughtworks.onlinebookstore.repository.IConsumerRepository;
import com.thoughtworks.onlinebookstore.repository.IOrderDetailsRepository;
import com.thoughtworks.onlinebookstore.utility.MailData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@SuppressWarnings("ALL")
@PropertySource("classpath:SuccessMessage.properties")
@Service
public class OrderConfirmationService {

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
    private SetDataForMail setDataForMail;

    private CountryType countryType;
    private OrderDetails orderDetails;
    private Book book;
    private Optional<Consumer> consumer;

    private String companyEmail = "talltalesbookchembur@gmail.com";
    private String backOfficeEmail = "talltalesbookbackoffice@gmail.com";

    public ResponseHelper confirmOrderAndSendMail(long consumerId) {
        consumer = consumerRepository.findById(consumerId);
        MailDto mailDto = new MailDto(consumer.get().getName(), consumer.get().getEmail(), book.getBookId(),
                book.getBookName(), book.getQuantity(), this.getTotalPrice());
        mailData.setMailData(mailDto);
        emailSender.send(setDataForMail.setDataForBackOffice());
        emailSender.send(setDataForMail.setDataForCustomer(companyEmail, mailDto.getConsumerEmail(),
                "TallTalesBooks Order Confirmation", mailData.getMailDataForCustomer()));
        saveOrderDetails();
        bookStoreServices.updateQuantity(this.book.getBookId(), this.book.getQuantity());
        return new ResponseHelper(200, environment.getProperty("status.mail.MailSentSuccessFully"));
    }

    private void saveOrderDetails() {
        orderDetails = new OrderDetails(book.getBookId(), book.getBookName(), consumer.get().getName(),
                consumer.get().getEmail(), this.getTotalPrice());
        orderDetailsRepository.save(orderDetails);
    }

    public double getTotalPrice() {
        if (consumer.get().getCountry().equalsIgnoreCase("India"))
            return ((this.book.getPrice() * this.book.getQuantity()) + countryType.INDIA.shippingCharges);
        return ((this.book.getPrice() * this.book.getQuantity()) + countryType.OTHER_COUNTRY.shippingCharges);
    }

    public Book getPurchasingBook(int id, int quantity) {
        Book bookById = bookStoreServices.getBookById(id);
        this.book = new Book(bookById.getBookId(), bookById.getBookName(), bookById.getPrice(), quantity);
        return bookById;
    }
}



