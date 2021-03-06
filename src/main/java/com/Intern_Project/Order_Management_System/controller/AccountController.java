package com.Intern_Project.Order_Management_System.controller;

import com.Intern_Project.Order_Management_System.exception.AccountNotExistException;
import com.Intern_Project.Order_Management_System.model.Account;
import com.Intern_Project.Order_Management_System.service.AccountService;
import com.Intern_Project.Order_Management_System.util.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String URL_REGISTER="/register";
    private static final String URL_LOGIN="/login";
    private static final String URL_ID="{id}";

    @PostMapping(value=URL_REGISTER)
    ResponseEntity<ResponseJson> registerAccount(@Valid @RequestBody Account account){
        accountService.createAccount(account);
        return new ResponseEntity<>(new ResponseJson("New Account has been Successfully Created"),HttpStatus.CREATED);
    }

    @GetMapping(value=URL_LOGIN)
    ResponseEntity<ResponseJson> authenticateUserCredentials(@RequestParam String userName,@RequestParam String password) {
        return accountService.validateAuthentication(userName,password);
    }
    @GetMapping()
    List<Account> getAllAccounts(){
        return accountService.findAllAccounts();
    }

    @GetMapping(value=URL_ID)
    Account getAccountByAccountId(@PathVariable(value="id") Integer id) {
        return accountService.findById(id);
    }

    @PutMapping()
    ResponseEntity<ResponseJson> updateAccountDetails(@Valid @RequestBody Account account) throws AccountNotExistException {
             accountService.updateAccount(account);
             return new ResponseEntity<>(new ResponseJson("Account details with Account Id "+account.getAccountId()+"  has been updated"),HttpStatus.OK);
    }

    @DeleteMapping(value = URL_ID)
    ResponseEntity<ResponseJson> deleteAccountByAccountId(@PathVariable Integer id){
        accountService.deleteAccount(id);
        return new ResponseEntity<>(new ResponseJson("Account  with Account Id "+id+"  has been deleted"),HttpStatus.OK);
    }
}
