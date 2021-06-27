package com.Intern_Project.Order_Management_System.repository;

import com.Intern_Project.Order_Management_System.model.Account;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface AccountRepository {

    void createTable();
    void insertAccount(Account account);
    List<Account> findAllAccounts();
    Account findByAccountId(Integer id) throws AccountNotFoundException;
    void updateAccount(Account account);
    void deleteAccountById(Integer id);

}
