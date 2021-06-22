package com.Intern_Project.Order_Management_System.exception;

import com.Intern_Project.Order_Management_System.util.Error.ErrorMessage;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.AccountNotFoundException;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<FieldError> fieldErrors=exception.getBindingResult().getFieldErrors();
        List<ErrorMessage> fieldErrorMessage=fieldErrors.stream().map(fieldError -> new ErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
        return fieldErrorMessage;
    }

    @ExceptionHandler(ValidationException.class)
    ErrorMessage handleValidationException(ValidationException exception){
        return new ErrorMessage("Error Code:200", exception.getMessage());
    }

    /*
    @ExceptionHandler(AccountNotFoundException.class)
    ResponseEntity<ErrorMessage> handleAccountNotFoundException(AccountNotFoundException exception){
        return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

     */

    @ExceptionHandler(EmptyResultDataAccessException.class)
    ErrorMessage handleEmptyResultDataAccessException(EmptyResultDataAccessException exception){
        return new ErrorMessage("Error Code:400","Account with given id not found");
    }
}

