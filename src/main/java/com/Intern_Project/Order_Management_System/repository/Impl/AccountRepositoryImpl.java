package com.Intern_Project.Order_Management_System.repository.Impl;

import com.Intern_Project.Order_Management_System.model.Account;
import com.Intern_Project.Order_Management_System.repository.AccountRepository;
import com.Intern_Project.Order_Management_System.util.RowMapper.AccountRowMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Repository("accountRepository")
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedTemplate=new NamedParameterJdbcTemplate(new JdbcTemplate());

    private static final String CREATE_TABLE="CREATE TABLE IF NOT EXISTS Account(account_id int IDENTITY primary key , first_name varchar(255), last_name  varchar(255) , user_name  varchar(255) , password    varchar(255) , address     varchar(255) , email_id    varchar(255) , phone_number varchar(255))";
    private static final String INSERT_ACCOUNT="insert into account (first_name,last_name,user_name,password,address,email_id,phone_number) values (:first_name,:last_name,:user_name,:password,:address,:email_id,:phone_number)";
    private static final String GET_ALL_ACCOUNTS="Select * from Account";
    private static final String GET_ACCOUNT_DETAILS_BY_ID="Select * from Account where account_id = :account_id";
    private static final String UPDATE_ACCOUNT="Update Account set first_name= :first_name , last_name = :last_name , user_name = :user_name ,password = :password , address = :address ,email_id = :email_id ,phone_number = :phone_number where account_id = :account_id";
    private static final String DELETE_ACCOUNT="Delete Account where account_id = :account_id";

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

    /*public AccountRepositoryImpl(){
    }*/

    public void createTable(){

        //String query="CREATE TABLE IF NOT EXISTS Account(account_id int IDENTITY primary key , first_name varchar(255), last_name  varchar(255) , user_name  varchar(255) , password    varchar(255) , address     varchar(255) , email_id    varchar(255) , phone_number varchar(255))";
        int count=this.jdbcTemplate.update(CREATE_TABLE);
        System.out.println("User table created "+count);
    }

    public Account createAccount(Account account){
        setParamMap(account);
        System.out.println("In Repository repo");
        int q=this.namedTemplate.update(INSERT_ACCOUNT, paramMap);
        System.out.println("Command Executed  "+q);
        return account;
    }

    public List<Account> findAll(){
        List<Account> accounts=new ArrayList<>();
        accounts=this.namedTemplate.query(GET_ALL_ACCOUNTS,AccountRowMapper.INSTANCE);
        return accounts;
    }

    public Account findById(Integer id){
        paramMap.put("account_id",id);
        //String query="Select * from Account where account_id = :account_id";
        Account account=this.namedTemplate.queryForObject(GET_ACCOUNT_DETAILS_BY_ID,paramMap,AccountRowMapper.INSTANCE);
        System.out.println("Print Account");
        System.out.println(account);
        return account;
    }

    public Account updateAccount(Account account){
        setParamMap(account);
       // String query="Update Account set first_name= :first_name , last_name = :last_name , user_name = :user_name ,password = :password , address = :address ,email_id = :email_id ,phone_number = :phone_number where account_id = :account_id ";
        this.namedTemplate.update(UPDATE_ACCOUNT,paramMap);
        return account;
    }

    public void deleteAccount(Integer id){
        paramMap.put("account_id",id);
        Account account=findById(id);
        //String query="Delete Account where account_id = :account_id";
        namedTemplate.update(DELETE_ACCOUNT,paramMap);
    }


}

//account_id,first_name,last_name,user_name,password,address,email_id,phone_number