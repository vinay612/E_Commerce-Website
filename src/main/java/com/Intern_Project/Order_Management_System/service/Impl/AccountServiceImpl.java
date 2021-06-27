package com.Intern_Project.Order_Management_System.service.Impl;

import com.Intern_Project.Order_Management_System.model.Account;
import com.Intern_Project.Order_Management_System.repository.AccountRepository;
import com.Intern_Project.Order_Management_System.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void createTable(){
        accountRepository.createTable();
    }

    public Account createAccount(Account account){
        System.out.println("In Service repo");
        return accountRepository.createAccount(account);
    }

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public Account findById(Integer id)  throws AccountNotFoundException {
        return accountRepository.findById(id);
    }

    public Account updateAccount(Account account){
        return accountRepository.updateAccount(account);
    }

    public void deleteAccount(Integer id){
        accountRepository.deleteAccount(id);
        return;
    }
}
