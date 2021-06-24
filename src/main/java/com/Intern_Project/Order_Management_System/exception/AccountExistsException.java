package com.Intern_Project.Order_Management_System.exception;

public class AccountExistsException extends RuntimeException {
    private String message;

    public AccountExistsException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
