package com.Intern_Project.Order_Management_System.model;
import javax.validation.constraints.*;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

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
    @Range(message = "Phone Number must be only numeric with 10 digits",min = 1000000000)
    @Digits(integer = 10,fraction = 0,message = "Phone Number must be only numeric with 10 digits")
    private long phoneNumber;

}
