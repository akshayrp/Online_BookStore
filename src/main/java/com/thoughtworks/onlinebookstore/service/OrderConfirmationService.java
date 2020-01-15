package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.dto.BookDto;
import com.thoughtworks.onlinebookstore.exception.BookStoreException;
import com.thoughtworks.onlinebookstore.model.Consumer;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class OrderConfirmationService {
    
    public OrderConfirmationService() {
    }
    public String setDetails(Consumer consumer) throws BookStoreException {
        Pattern patternForName = Pattern.compile("^[A-Z]{1}[a-z]{2,}");
        Matcher matchObjName = patternForName.matcher(consumer.getName());

        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9]([-._+]{0,1}[a-zA-Z0-9])*[@]{1}[a-zA-Z0-9]{1,}[.]{1}[a-zA-Z]{2,3}([.]{1}[a-zA-Z]{2,3}){0,1}$");
        Matcher matcherForEmail = emailPattern.matcher(consumer.getEmail());

        Pattern patternForPin = Pattern.compile("^[1-9][0-9]{5}$");
        Matcher matcherForPin = patternForPin.matcher(consumer.getPinCode());

        if (matchObjName.matches() && matcherForEmail.matches() && matcherForPin.matches()) {
            return consumer.toString();
        }
            throw new BookStoreException("invalid details..please check your entered data",BookStoreException
                    .ExceptionType.INVALID_DETAIL);
    }
}
