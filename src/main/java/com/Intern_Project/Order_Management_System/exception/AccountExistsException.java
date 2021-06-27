package com.Intern_Project.Order_Management_System.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountExistsException extends RuntimeException {
    private String message;

}
