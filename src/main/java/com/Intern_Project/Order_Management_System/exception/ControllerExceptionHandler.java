package com.Intern_Project.Order_Management_System.exception;

import com.Intern_Project.Order_Management_System.util.Error.ErrorMessage;
import com.Intern_Project.Order_Management_System.util.ResponseJson;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
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

    private final Logger log= LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<FieldError> fieldErrors=exception.getBindingResult().getFieldErrors();
        return fieldErrors.stream().map(fieldError -> new ErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<ResponseJson> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        return new ResponseEntity<>(new ResponseJson(exception.getLocalizedMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    ResponseEntity<ResponseJson> handleInvalidFormatException(InvalidFormatException exception){
        return new ResponseEntity<>(new ResponseJson(exception.getMessage()),HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(ValidationException.class)
    ResponseEntity<ResponseJson> handleValidationException(ValidationException exception){
        return new ResponseEntity<>(new ResponseJson(exception.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountExistsException.class)
    ResponseEntity<ResponseJson> handleAccountExistsException(AccountExistsException exception){
        return new ResponseEntity<>(new ResponseJson(exception.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    ResponseEntity<ResponseJson> handleAccountNotFoundException(AccountNotFoundException exception){
        return new ResponseEntity<>(new ResponseJson(exception.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<ResponseJson> handleDataIntegrityViolationException(DataIntegrityViolationException exception){
        return new ResponseEntity<>(new ResponseJson(exception.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    ResponseEntity<ResponseJson> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception){
        return new ResponseEntity<>(new ResponseJson("Entity Not Found"),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseJson> handleException(Exception exception) {
        return new ResponseEntity<>(new ResponseJson("Entity not Found"),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

