package com.thoughtworks.onlinebookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class ConsumerDto {

    @NotBlank(message = "Please Enter Valid Country")
    @Pattern(regexp = ("^[A-Za-z]{2,}"),message = "Enter Proper Country Name")
    private String country;

    @NotBlank(message = "Please Enter Valid Email")
    @Pattern(regexp = ("^[a-zA-Z0-9]([-._+]{0,1}[a-zA-Z0-9])*[@]{1}[a-zA-Z0-9]{1,}[.]{1}[a-zA-Z]{2,3}([.]{1}[a-zA-Z]{2,3}){0,1}$"),
            message = "Should have atleast 1 character,1 number")
    private String email;

    @NotBlank(message = "Please Enter Valid Address")
    private String address;

    @NotBlank(message = "Please Enter Valid Consumer Name")
    @Pattern(regexp = ("^[A-Z]{1}[a-zA-Z]{2,}"),message = "First Letter Should be Capital")
    private String name;

    @NotBlank(message = "Please Enter Valid Pin")
    @Pattern(regexp = ("^[1-9][0-9]{5}$"),message = "Should be 6 digit code")
    private String pinCode;

}
