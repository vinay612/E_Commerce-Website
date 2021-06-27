package com.Intern_Project.Order_Management_System.exception;

import com.Intern_Project.Order_Management_System.util.Error.ErrorMessage;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.AccountNotFoundException;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<FieldError> fieldErrors=exception.getBindingResult().getFieldErrors();
        List<ErrorMessage> fieldErrorMessage=fieldErrors.stream().map(fieldError -> new ErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
        return fieldErrorMessage;
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        return new ResponseEntity(exception.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    ResponseEntity<String> handleInvalidFormatException(InvalidFormatException exception){
        return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(ValidationException.class)
    ResponseEntity<String> handleValidationException(ValidationException exception){
        return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountExistsException.class)
    ResponseEntity<String> handleAccountExistsException(AccountExistsException exception){
        return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException exception){
        return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(EmptyResultDataAccessException.class)
    ResponseEntity<String> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception){
        return new ResponseEntity("Account with given id not found",HttpStatus.BAD_REQUEST);
    }
}

