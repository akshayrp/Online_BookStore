package com.thoughtworks.onlinebookstore.model;

import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private String name;
    private String email;
    private String address;
    private CharSequence pinCode;
    private String country;

    public Consumer(String name, String email, String address, CharSequence pinCode, String country) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.pinCode = pinCode;
        this.country.equalsIgnoreCase(country);
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

    public String getCountry() {
        return country;
    }

    public Consumer() {
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
