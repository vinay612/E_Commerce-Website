package com.Intern_Project.Order_Management_System.controller;

import com.Intern_Project.Order_Management_System.exception.AccountExistsException;
import com.Intern_Project.Order_Management_System.model.Account;
import com.Intern_Project.Order_Management_System.service.AccountService;
import com.Intern_Project.Order_Management_System.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    ResponseEntity<String> postAccount(@Valid @RequestBody Account account) throws AccountExistsException {
        System.out.println("In Controller repo");
        List<Account> accounts=accountService.findAllAccounts();
        accounts.forEach(user ->{
            if(user.getEmailId().equalsIgnoreCase(account.getEmailId()))
                throw new AccountExistsException("Account with the given email Id already Exists");
            else if(user.getPhoneNumber().equals(account.getPhoneNumber()))
                throw new AccountExistsException("Account with the given phone number already Exists");
            else if(user.getUserName().equalsIgnoreCase(account.getUserName()))
                throw new AccountExistsException("Account with this user name already Exists");
        });
        accountService.createAccount(account);
        return new ResponseEntity("New Account has been Successfully Created",HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    ResponseEntity<String> logIn(@RequestParam String userName,@RequestParam String password) throws AccountNotFoundException {
        List<Account> accounts=accountService.findAllAccounts();
        for(Account account : accounts){
            if(account.getUserName().equalsIgnoreCase(userName) && account.getPassword().equals(password))
                return new ResponseEntity("Account Credentials has been Authenticated.Log in Approved",HttpStatus.ACCEPTED);
        }
        throw new AccountNotFoundException("User Name OR Password is/are incorrect");
    }
    @GetMapping()
    List<Account> getAllAccount(){
        return accountService.findAllAccounts();
    }

    @GetMapping("{id}")
    Account getAccountById(@PathVariable(value="id") Integer id)  throws AccountNotFoundException {
        return accountService.findById(id);
    }

    @PutMapping()
    ResponseEntity<String> putAccount(@Valid @RequestBody Account account) throws AccountNotFoundException{
        account=accountService.findById(account.getAccountId());
             accountService.updateAccount(account);
             return new ResponseEntity("Account details with Account Id :"+account.getAccountId()+"  has been updated",HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteAccount(@PathVariable Integer id){
        accountService.deleteAccount(id);
        return new ResponseEntity("Account  with Account Id :"+id+"  has been deleted",HttpStatus.ACCEPTED);
    }
}
