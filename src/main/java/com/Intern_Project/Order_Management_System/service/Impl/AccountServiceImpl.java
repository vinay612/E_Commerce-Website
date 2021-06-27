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

    public void createAccount(Account account){
        System.out.println("In Service repo");
        accountRepository.insertAccount(account);
        return;
    }

    public List<Account> findAllAccounts(){
        return accountRepository.findAllAccounts();
    }

    public Account findById(Integer id)  throws AccountNotFoundException {
        return accountRepository.findByAccountId(id);
    }

    public void updateAccount(Account account){
        accountRepository.updateAccount(account);
        return;
    }

    public void deleteAccount(Integer id){
        accountRepository.deleteAccountById(id);
        return;
    }
}
