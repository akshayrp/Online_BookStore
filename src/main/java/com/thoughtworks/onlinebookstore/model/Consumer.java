package com.thoughtworks.onlinebookstore.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
@Data
public class Consumer {

    @Id
    private long consumerId;

    private String country;

    private String name;

    private String email;

    private String address;

    private String pinCode;

    public Consumer() { }

    public Consumer(String name, String email, String address, String pinCode, String country) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.pinCode = pinCode;
        this.country = country;
    }



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

    @Override
    public String toString() {
        return "Consumer{" +
                "consumerId=" + consumerId +
                ", country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", pinCode='" + pinCode + '\'' +
                '}';
    }
}

