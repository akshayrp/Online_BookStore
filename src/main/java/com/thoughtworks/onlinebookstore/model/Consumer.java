package com.thoughtworks.onlinebookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long consumerId;
    @NotBlank(message = "Please Enter Valid Country")
    private String country;

    @NotBlank(message = "Please Enter Valid Consumer Name")
    @Pattern(regexp = ("^[A-Z]{1}[a-z]{2,}"))
    private String name;

    @NotBlank(message = "Please Enter Valid Email")
    @Pattern(regexp = ("^[a-zA-Z0-9]([-._+]{0,1}[a-zA-Z0-9])*[@]{1}[a-zA-Z0-9]{1,}[.]{1}[a-zA-Z]{2,3}([.]{1}[a-zA-Z]{2,3}){0,1}$"))
    private String email;

    @NotBlank(message = "Please Enter Valid Address")
    private String address;

    @NotBlank(message = "Please Enter Valid Pin")
    @Pattern(regexp = ("^[1-9][0-9]{5}$"))
    private String pinCode;

    public Consumer(String name, String email, String address, String pinCode, String country) {

        this.name = name;
        this.email = email;
        this.address = address;
        this.pinCode = pinCode;
        this.country = country;
    }
}

