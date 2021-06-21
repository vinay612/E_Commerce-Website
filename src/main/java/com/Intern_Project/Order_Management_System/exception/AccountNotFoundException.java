package com.Intern_Project.Order_Management_System.exception;

public class AccountNotFoundException extends RuntimeException{

    private Integer code;
    private String message;

    public AccountNotFoundException(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
