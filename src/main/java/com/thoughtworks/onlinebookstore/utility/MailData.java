package com.thoughtworks.onlinebookstore.utility;

import com.thoughtworks.onlinebookstore.dto.MailDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Service
public class MailData {
    public MailData() {
    }

    private MailDto mailDto;

    private String bookingTime = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    private String Header = "\t\t\t\t\t\t\t\t\tPurchase Order Acceptance Letter\n\n";
    private String shopName = "\t\t\t\t\t\t\t\t\t\tTall Tales Book Shop Ltd.\n\n";
    private String shopAdd = "Tall Tales Book Shop,\nMalhotra Chambers,\nFirst floor, Govandi East,\nMumbai, Maharashtra 400088\n\n";
    private String sincere = "Sincerely,\nTall Tales Book Shop\ntall-tales-book@gmail.com\n";


    private String companyEmail = "talltalesbookchembur@gmail.com";
    private String backOfficeEmail = "talltalesbookbackoffice@gmail.com";
    /////////////Customer Mail Details


    /////////////BackOffice Mail Details
    private String managerGreetings = "Dear Manager,\n\n";
    private String backOfficeMailData = "Hurray another order placed for us.\n" +
            "Following is the order details to be completed.\n\n";

    public String getMailDataForCustomer() {
        return Header + shopName + bookingTime + "\n\n" + shopAdd + "Dear  " + mailDto.getConsumerName() + ",\n\n" + "We acknowledge the receipt of your purchase order " + mailDto.getBookId() + ". We are pleased to " +
                "accept your order and look forward to doing business with you.\n\nAs per the terms outlined in our quote, delivery is from four to six weeks from the date of the order.\n" +
                "Should you have any queries regarding your order, please call our customer support number or get in touch with us directly via email.\n\n"
                + "Thank you again for your order and business.\n\n" + sincere;
    }

    public String getMailDataForBackOffice() {
        return Header + shopName + bookingTime + "\n\n" + shopAdd + managerGreetings + backOfficeMailData +
                "Order Number : " + mailDto.getBookId() + " | Order Date : "
                + bookingTime +
                " | Recipient Name : " +
                mailDto.getConsumerName() + "\nBook Name : " +
                mailDto.getBookTitle() +
                " | Quantity : " +
                mailDto.getBookQuantity() + " | Total Book Price : " + mailDto.getTotalPrice() + "\n\n" + sincere;
    }

    public void setMailData(MailDto mailDto) {
        this.mailDto = mailDto;
    }
}
