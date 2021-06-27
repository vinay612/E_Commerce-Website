package com.Intern_Project.Order_Management_System.model;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public class Account {

    private int accountId;
    @NotBlank(message="FirstName cannot be blank")
    private String firstName;
    private String lastName;
    @NotBlank(message = "user name cannot be blank")
    private String userName;
    @NotBlank(message = "password cannot be blank")
    private String password;
    @NotBlank(message = "address cannot be blank")
    private String address;
    @Email(message = "EmailId is not valid")
    private String emailId;
    @NotNull(message = "Phone Number must be only numeric with 10 digits")
    @Pattern(regexp = "^[0-9]{10}$",message = "Phone Number must be only numeric with 10 digits")
    private String phoneNumber;

}
