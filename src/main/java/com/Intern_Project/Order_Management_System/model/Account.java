package com.Intern_Project.Order_Management_System.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

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
    //@NotBlank(message = "phone number cannot be blank")
    //@Length(max = 10,min = 10)
    @NotNull(message = "Phone Number must be only numeric with 10 digits")
    @Pattern(regexp = "^[0-9]{10}$",message = "Phone Number must be only numeric with 10 digits")
    private String phoneNumber;


    public Account() {
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
