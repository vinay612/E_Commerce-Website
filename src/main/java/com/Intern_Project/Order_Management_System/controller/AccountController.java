package com.Intern_Project.Order_Management_System.controller;

import com.Intern_Project.Order_Management_System.model.Account;
import com.Intern_Project.Order_Management_System.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    private static final String URL_REGISTER="/register";
    private static final String URL_LOGIN="/login";
    private static final String URL_ID="{id}";

    @PostMapping(value=URL_REGISTER)
    ResponseEntity<String> postAccount(@Valid @RequestBody Account account){
        accountService.createAccount(account);
        return new ResponseEntity("New Account has been Successfully Created",HttpStatus.ACCEPTED);
    }

    @PostMapping(value=URL_LOGIN)
    ResponseEntity<String> userCredentialsAuthentication(@RequestParam String userName,@RequestParam String password) throws AccountNotFoundException {
        return accountService.authenticationValidation(userName,password);
    }
    @GetMapping()
    List<Account> getAllAccounts(){
        return accountService.findAllAccounts();
    }

    @GetMapping(value=URL_ID)
    Account getAccountById(@PathVariable(value="id") Integer id)  throws AccountNotFoundException {
        return accountService.findById(id);
    }

    @PutMapping()
    ResponseEntity<String> updateAccountById(@Valid @RequestBody Account account) throws AccountNotFoundException{
             accountService.updateAccount(account);
             return new ResponseEntity("Account details with Account Id :"+account.getAccountId()+"  has been updated",HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = URL_ID)
    ResponseEntity<String> deleteAccountById(@PathVariable Integer id){
        accountService.deleteAccount(id);
        return new ResponseEntity("Account  with Account Id :"+id+"  has been deleted",HttpStatus.ACCEPTED);
    }
}
