package com.thoughtworks.onlinebookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long consumerId;
    private String country;
    private String name;
    private String email;
    private String address;
    private String pinCode;

    public long getConsumerId() {
        return consumerId;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPinCode() {
        return pinCode;
    }
}
