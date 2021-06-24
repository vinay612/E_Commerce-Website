package com.Intern_Project.Order_Management_System.controller;

import com.Intern_Project.Order_Management_System.exception.AccountExistsException;
import com.Intern_Project.Order_Management_System.model.Account;
import com.Intern_Project.Order_Management_System.service.AccountService;
import com.Intern_Project.Order_Management_System.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account/register")
    Status postAccount(@Valid @RequestBody Account account) throws AccountExistsException {
        System.out.println("In Controller repo");
        List<Account> accounts=accountService.findAll();
        accounts.forEach(user ->{
            if(user.getEmailId().equalsIgnoreCase(account.getEmailId()))
                throw new AccountExistsException("Account with the given email Id already Exists");
            else if(user.getPhoneNumber().equals(account.getPhoneNumber()))
                throw new AccountExistsException("Account with the given phone number already Exists");
            else if(user.getUserName().equalsIgnoreCase(account.getUserName()))
                throw new AccountExistsException("Account with this user name already Exists");
        });
        accountService.createAccount(account);
        return Status.SUCCESS;
    }

    @PostMapping("/account/login")
    Status logIn(@RequestParam String userName,@RequestParam String password) throws AccountNotFoundException {
        List<Account> accounts=accountService.findAll();
        for(Account account : accounts){
            if(account.getUserName().equalsIgnoreCase(userName) && account.getPassword().equals(password))
                return Status.SUCCESS;
        }
        throw new AccountNotFoundException("User Name OR Password is/are incorrect");
    }
    @GetMapping("/account")
    List<Account> getAllAccount(){
        return accountService.findAll();
    }

    @GetMapping("/account/{id}")
    Account getAccountById(@PathVariable(value="id") @Max(value = 100) Integer id)  throws AccountNotFoundException {
        return accountService.findById(id);
    }

    @PutMapping("/account")
    Account putAccount(@Valid @RequestBody Account account) throws AccountNotFoundException{
        account=accountService.findById(account.getAccountId());
            return accountService.updateAccount(account);
    }

    @DeleteMapping("/account/{id}")
    Status deleteAccount(@PathVariable @Max(value=100) Integer id){
        accountService.deleteAccount(id);
        return Status.SUCCESS;
    }
}
