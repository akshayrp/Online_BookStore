package com.thoughtworks.onlinebookstore.utility;

import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.model.Book;
import com.thoughtworks.onlinebookstore.model.Consumer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class MailData {

    private static Book bookDetails = new Book();

    private static Consumer consumer = new Consumer();

    private static String bookingTime = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));


    private static String Header = "\t\t\t\t\t\t\t\t\tPurchase Order Acceptance Letter\n\n";
    private static String shopName = "\t\t\t\t\t\t\t\t\t\tTall Tales Book Shop Ltd.\n\n";
    private static String shopAdd = "Tall Tales Book Shop,\nMalhotra Chambers,\nFirst floor, Govandi East,\nMumbai, Maharashtra 400088\n\n";
    private static String sincere = "Sincerely,\nTall Tales Book Shop\ntall-tales-book@gmail.com\n";

    /////////////Customer Mail Details
   private static String greetings = "Dear  " + consumer.getName() + ",\n\n";
    private static String ack = "We acknowledge the receipt of your purchase order " + bookDetails.getId() + ". We are pleased to " +
            "accept your order and look forward to doing business with you.\n\nAs per the terms outlined in our quote, delivery is from four to six weeks from the date of the order.\n" +
            "Should you have any queries regarding your order, please call our customer support number or get in touch with us directly via email.\n\n"
            + "Thank you again for your order and business.\n\n";


    /////////////BackOffice Mail Details
   private static String managerGreetings = "Dear Manager,\n\n";
   private static String backOfficeMailData = "Hurray another order placed for us.\n" +
            "Following is the order details to be completed.\n\n";
    private static String backOfficeOrderDetails = "Order Number : " + bookDetails.getId() + " | Order Date : " + bookingTime +
            " | Recipient Name : " + consumer.getName() + "\nBook Name : " + bookDetails.getTitle() +
            " | Quantity : " + bookDetails.getSelectedQuantity() + " | Total Book Price : " + "/////has to get from user model\n\n";


    public static String getMailDataForCustomer() {
        return Header + shopName + bookingTime + "\n\n" + shopAdd + greetings + ack + sincere;
    }

    public static String getMailDataForBackOffice() {
        return Header + shopName + bookingTime + "\n\n" + shopAdd + managerGreetings + backOfficeMailData + backOfficeOrderDetails + sincere;
    }
}
