package com.Intern_Project.Order_Management_System.controller;

import com.Intern_Project.Order_Management_System.model.Account;
import com.Intern_Project.Order_Management_System.service.AccountService;
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

    @PostMapping("/account")
    Account postAccount(@Valid @RequestBody Account account){
        System.out.println("In Controller repo");
        return accountService.createAccount(account);
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
    Account deleteAccount(@PathVariable @Max(value=100) Integer id){
        accountService.deleteAccount(id);
        return null;
    }
}
