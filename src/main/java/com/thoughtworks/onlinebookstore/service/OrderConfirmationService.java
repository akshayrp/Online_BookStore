package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.Response.Response;
import com.thoughtworks.onlinebookstore.Response.ResponseHelper;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.model.Books;
import com.thoughtworks.onlinebookstore.model.Consumer;
import com.thoughtworks.onlinebookstore.model.OrderDetails;
import com.thoughtworks.onlinebookstore.repository.IBookShopRepository;
import com.thoughtworks.onlinebookstore.repository.IOrderDetailsRepository;
import com.thoughtworks.onlinebookstore.utility.MailData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@PropertySource("classpath:SuccessMessage.properties")
@Service
public class OrderConfirmationService {
    @Autowired
    IBookShopRepository bookShopRepository;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private Environment environment;
    @Autowired
    IOrderDetailsRepository orderDetailsRepository;

    private OrderDetails orderDetails;
    private Book book;
    private Consumer consumer;

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
        book = new Book(byId.getId(), byId.getTitle(), byId.getPrice(), quantity);
        return book;
    }

    public Response confirmOrderAndSendMail() {

        emailSender.send(setDataForCustomer(companyEmail, "akshaypatwari24@gmail.com"/*consumer.getEmail()*/,
                "TallTalesBooks Order Confirmation", MailData.getMailDataForCustomer()));

        emailSender.send(setDataForBackOffice(companyEmail));

        Response response = ResponseHelper.statusResponse(200,
                environment.getProperty("status.mail.MailSentSuccessFully"));
        saveOrderDetails();
        updateQuantity();
        return response;
    }

    private void saveOrderDetails() {
        orderDetails = new OrderDetails(book.getId(),book.getTitle(),/*consumer.getName()*/"Akshay",
                /*consumer.getEmail()*/"akshaypatwari24@gmail.com",120);
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
        backOfficeMessage.setText(MailData.getMailDataForBackOffice());
        return backOfficeMessage;
    }
}