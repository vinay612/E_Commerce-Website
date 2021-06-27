package com.Intern_Project.Order_Management_System.repository.Impl;

import com.Intern_Project.Order_Management_System.model.Account;
import com.Intern_Project.Order_Management_System.repository.AccountRepository;
import com.Intern_Project.Order_Management_System.util.RowMapper.AccountRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private Logger log= LoggerFactory.getLogger(this.getClass());

    //SQL Queries
    private static final String CREATE_TABLE="CREATE TABLE IF NOT EXISTS Account(account_id int IDENTITY primary key , first_name varchar(255), last_name  varchar(255) , user_name  varchar(255) , password    varchar(255) , address     varchar(255) , email_id    varchar(255) , phone_number varchar(255))";
    private static final String INSERT_ACCOUNT="insert into account (first_name,last_name,user_name,password,address,email_id,phone_number) values (:first_name,:last_name,:user_name,:password,:address,:email_id,:phone_number) ";
    private static final String SELECT_ALL="Select * from Account";
    private static final String SELECT_BY_ID="Select * from Account where account_id = :account_id";
    private static final String UPDATE_ACCOUNT="Update Account set first_name= :first_name , last_name = :last_name , user_name = :user_name ,password = :password , address = :address ,email_id = :email_id ,phone_number = :phone_number where account_id = :account_id ";
    private static final String DELETE_ACCOUNT="Delete Account where account_id = :account_id";


    public void createTable(){

        int count=this.jdbcTemplate.update(CREATE_TABLE);
        log.info("User table created {}",count);
    }

    public void insertAccount(Account account) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("first_name", account.getFirstName())
                .addValue("last_name", account.getLastName())
                .addValue("user_name", account.getUserName())
                .addValue("password", account.getPassword())
                .addValue("address", account.getAddress())
                .addValue("email_id", account.getEmailId())
                .addValue("phone_number", account.getPhoneNumber());
        log.info("In Repository repo {}", account.getAccountId());
        int q = this.namedParameterJdbcTemplate.update(INSERT_ACCOUNT, sqlParameterSource);
        log.info("A new Account has been created");
        return;
    }



    public List<Account> findAllAccounts(){
        List<Account> accounts=new ArrayList<>();
        accounts=this.namedParameterJdbcTemplate.query(SELECT_ALL,AccountRowMapper.INSTANCE);
        return accounts;
    }

    public Account findByAccountId(Integer id){

        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue("account_id",id);

        Account account=this.namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID,sqlParameterSource,AccountRowMapper.INSTANCE);
        log.info("Account with Account Id {} has been found",id);
        return account;
    }

    public void updateAccount(Account account){
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue("account_id",account.getAccountId());
        this.namedParameterJdbcTemplate.update(UPDATE_ACCOUNT,sqlParameterSource);
        return ;
    }

    public void deleteAccountById(Integer id){
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue("account_id",id);
        Account account=findByAccountId(id);
        this.namedParameterJdbcTemplate.update(DELETE_ACCOUNT,sqlParameterSource);
        return;
    }






}

//account_id,first_name,last_name,user_name,password,address,email_id,phone_number