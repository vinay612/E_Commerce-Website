package com.Intern_Project.Order_Management_System.exception;

import com.Intern_Project.Order_Management_System.util.Error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.AccountNotFoundException;
import javax.xml.bind.ValidationException;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        return new ErrorMessage(4090,exception.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    ErrorMessage handleValidationException(ValidationException exception){
        return new ErrorMessage(200, exception.getMessage());
    }

    @ExceptionHandler(AccountNotFoundException.class)
    ErrorMessage handleAccountNotFoundException(AccountNotFoundException exception){
        return new ErrorMessage(400,"Account Not Found with this ID : ");
    }
}

