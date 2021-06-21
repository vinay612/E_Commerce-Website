package com.Intern_Project.Order_Management_System.repository;

import com.Intern_Project.Order_Management_System.model.Account;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface AccountRepository {
    void createTable();
    Account createAccount(Account account);
    List<Account> findAll();
    Account findById(Integer id) throws AccountNotFoundException;
    Account updateAccount(Account account);
    void deleteAccount(Integer id);
}
