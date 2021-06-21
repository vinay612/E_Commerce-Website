package com.Intern_Project.Order_Management_System.util.Error;

public class ErrorMessage {
    private Integer code;
    private String message;

    public ErrorMessage(){

    }

    public ErrorMessage(Integer code,String message){
        this.code=code;
        this.message=message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
