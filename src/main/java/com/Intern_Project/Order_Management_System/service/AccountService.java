package com.Intern_Project.Order_Management_System.service;

import com.Intern_Project.Order_Management_System.model.Account;
import com.Intern_Project.Order_Management_System.util.ResponseJson;
import org.springframework.http.ResponseEntity;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface AccountService {

    void createTable();
    void createAccount(Account account);
    List<Account> findAllAccounts();
    Account findById(Integer id) throws AccountNotFoundException;
    void updateAccount(Account account) throws AccountNotFoundException;
    void deleteAccount(Integer id);
    ResponseEntity<ResponseJson> validateAuthentication(String userName, String password) throws AccountNotFoundException;

}
