package com.Intern_Project.Order_Management_System.util.Error;

public class ErrorMessage {
    private String field;
    private String message;

    public ErrorMessage(){

    }

    public ErrorMessage(String field,String message){
        this.field=field;
        this.message=message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
