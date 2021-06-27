package com.Intern_Project.Order_Management_System.service.Impl;

import com.Intern_Project.Order_Management_System.exception.AccountExistsException;
import com.Intern_Project.Order_Management_System.model.Account;
import com.Intern_Project.Order_Management_System.repository.AccountRepository;
import com.Intern_Project.Order_Management_System.service.AccountService;
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

    public void createTable(){
        accountRepository.createTable();
    }

    public void createAccount(Account account)  throws AccountExistsException {

        System.out.println("In Controller repo");
        List<Account> accounts=accountRepository.findAllAccounts();
        accounts.forEach(user ->{
            if(user.getEmailId().equalsIgnoreCase(account.getEmailId()))
                throw new AccountExistsException("Account with the given email Id already Exists");
            else if(user.getPhoneNumber().equals(account.getPhoneNumber()))
                throw new AccountExistsException("Account with the given phone number already Exists");
            else if(user.getUserName().equalsIgnoreCase(account.getUserName()))
                throw new AccountExistsException("Account with this user name already Exists");
        });
        System.out.println("In Service repo");
        accountRepository.insertAccount(account);
        return;
    }

    public ResponseEntity<String> authenticationValidation(String userName, String password) throws AccountNotFoundException {
        List<Account> accounts=accountRepository.findAllAccounts();
        for(Account account : accounts){
            if(account.getUserName().equalsIgnoreCase(userName) && account.getPassword().equals(password))
                return new ResponseEntity("Account Credentials has been Authenticated.Log in Approved", HttpStatus.ACCEPTED);
        }
        throw new AccountNotFoundException("User Name OR Password is/are incorrect");
    }

    public List<Account> findAllAccounts(){
        return accountRepository.findAllAccounts();
    }

    public Account findById(Integer id)  throws AccountNotFoundException {
        return accountRepository.findByAccountId(id);
    }

    public void updateAccount(Account account) throws AccountNotFoundException {
        account=accountRepository.findByAccountId(account.getAccountId());
        accountRepository.updateAccount(account);
        return;
    }

    public void deleteAccount(Integer id){
        accountRepository.deleteAccountById(id);
        return;
    }
}
