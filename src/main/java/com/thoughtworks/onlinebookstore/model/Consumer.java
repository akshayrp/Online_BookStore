package com.thoughtworks.onlinebookstore.model;

import com.thoughtworks.onlinebookstore.service.CountryType;

public class Consumer {
    private CountryType country;
    private String name;
    private String email;
    private String address;
    private CharSequence pinCode;

    public Consumer() {
    }

    public Consumer(String name, String email, String address, CharSequence pinCode, CountryType country) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.pinCode = pinCode;
        this.country = country;
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

    public CharSequence getPinCode() {
        return pinCode;
    }

    public CountryType getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", pinCode=" + pinCode +
                ", country='" + country + '\'' +
                '}';
    }
}
