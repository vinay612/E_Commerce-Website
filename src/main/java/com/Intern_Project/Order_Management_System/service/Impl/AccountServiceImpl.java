package com.Intern_Project.Order_Management_System.service.Impl;

import com.Intern_Project.Order_Management_System.exception.AccountExistsException;
import com.Intern_Project.Order_Management_System.model.Account;
import com.Intern_Project.Order_Management_System.repository.AccountRepository;
import com.Intern_Project.Order_Management_System.service.AccountService;
import com.Intern_Project.Order_Management_System.util.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    public void createTable(){
        accountRepository.createTable();
    }

    public void createAccount(Account account)  throws AccountExistsException {

        List<Account> accounts=accountRepository.findAllAccounts();
        accounts.forEach(user ->{
            if(user.getEmailId().equalsIgnoreCase(account.getEmailId())) {
                log.info("Account with emailId {} already exists. New account cannot be created",account.getEmailId());
                throw new AccountExistsException("Account with the given email Id already Exists");
            }
            else if(user.getPhoneNumber() == account.getPhoneNumber()) {
                log.info("Account with Phone Number {} already exists. New account cannot be created",account.getPhoneNumber());
                throw new AccountExistsException("Account with the given phone number already Exists");
            }
            else if(user.getUserName().equalsIgnoreCase(account.getUserName())) {
                log.info("Account with UserName {} already exists. New account cannot be created",account.getUserName());
                throw new AccountExistsException("Account with this user name already Exists");
            }
        });
        accountRepository.insertAccount(account);
    }

    public ResponseEntity<ResponseJson> validateAuthentication(String userName, String password) throws AccountNotFoundException {
        List<Account> accounts=accountRepository.findAllAccounts();
        for(Account account : accounts){
            if(account.getUserName().equalsIgnoreCase(userName) && account.getPassword().equals(password))
                log.info("Account Credentials has been Authenticated.Log in Approved");
                return new ResponseEntity<>(new ResponseJson("Account Credentials has been Authenticated.Log in Approved"), HttpStatus.OK);
        }
        log.info("UserName {} or Password {} is/are incorrect",userName,password);
        throw new AccountNotFoundException("User Name OR Password is/are incorrect");
    }

    public List<Account> findAllAccounts(){
        return accountRepository.findAllAccounts();
    }

    public Account findById(Integer id)  throws AccountNotFoundException {
        return accountRepository.findByAccountId(id);
    }

    public void updateAccount(Account account) throws AccountNotFoundException {
        accountRepository.updateAccount(account);
    }

    public void deleteAccount(Integer id){
        accountRepository.deleteAccountById(id);
    }
}
