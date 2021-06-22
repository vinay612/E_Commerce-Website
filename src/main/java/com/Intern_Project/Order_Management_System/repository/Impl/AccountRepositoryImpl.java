package com.Intern_Project.Order_Management_System.repository.Impl;

import com.Intern_Project.Order_Management_System.model.Account;
import com.Intern_Project.Order_Management_System.repository.AccountRepository;
import com.Intern_Project.Order_Management_System.util.RowMapper.AccountRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("accountRepository")
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedTemplate=new NamedParameterJdbcTemplate(new JdbcTemplate());

    Map<String,Object> paramMap=new HashMap<>();

    public void setParamMap(Account account){

        paramMap.put("account_id",account.getAccountId());
        paramMap.put("first_name",account.getFirstName());
        paramMap.put("last_name",account.getLastName());
        paramMap.put("user_name",account.getUserName());
        paramMap.put("password",account.getPassword());
        paramMap.put("address",account.getAddress());
        paramMap.put("email_id",account.getEmailId());
        paramMap.put("phone_number",account.getPhoneNumber());
        return;

    }

    public AccountRepositoryImpl(){
    }

    public void createTable(){

        String query="CREATE TABLE IF NOT EXISTS Account(account_id int primary key , first_name varchar(255), last_name  varchar(255) , user_name  varchar(255) , password    varchar(255) , address     varchar(255) , email_id    varchar(255) , phone_number varchar(255))";
        int count=this.jdbcTemplate.update(query);
        System.out.println("User table created "+count);
    }

    public Account createAccount(Account account){
        setParamMap(account);
        System.out.println("In Repository repo");
        int q=this.namedTemplate.update("insert into account (account_id,first_name,last_name,user_name,password,address,email_id,phone_number) values (:account_id,:first_name,:last_name,:user_name,:password,:address,:email_id,:phone_number) ", paramMap);
        System.out.println("Command Executed  "+q);
        return account;
    }

    public List<Account> findAll(){
        List<Account> accounts=new ArrayList<>();
        accounts=this.namedTemplate.query("Select * from Account",new AccountRowMapper());
        return accounts;
    }

    public Account findById(Integer id){
        paramMap.put("account_id",id);
        String query="Select * from Account where account_id = :account_id";
        Account account=this.namedTemplate.queryForObject(query,paramMap,new AccountRowMapper());
        System.out.println("Print Account");
        System.out.println(account);
        return account;
    }

    public Account updateAccount(Account account){
        setParamMap(account);
        String query="Update Account set first_name= :first_name , last_name = :last_name , user_name = :user_name ,password = :password , address = :address ,email_id = :email_id ,phone_number = :phone_number where account_id = :account_id ";
        this.namedTemplate.update(query,paramMap);
        return account;
    }

    public void deleteAccount(Integer id){
        paramMap.put("account_id",id);
        Account account=findById(id);
        String query="Delete Account where account_id = :account_id";
        namedTemplate.update(query,paramMap);
    }


}

//account_id,first_name,last_name,user_name,password,address,email_id,phone_number