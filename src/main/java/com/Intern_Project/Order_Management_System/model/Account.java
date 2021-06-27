package com.Intern_Project.Order_Management_System.model;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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

}
