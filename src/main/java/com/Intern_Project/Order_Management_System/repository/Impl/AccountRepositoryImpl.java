package com.Intern_Project.Order_Management_System.repository.Impl;

import com.Intern_Project.Order_Management_System.exception.AccountNotFoundException;
import com.Intern_Project.Order_Management_System.model.Account;
import com.Intern_Project.Order_Management_System.repository.AccountRepository;
import com.Intern_Project.Order_Management_System.util.ApplicationConstants;
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

import java.util.List;

@NoArgsConstructor
@Repository("accountRepository")
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final Logger log= LoggerFactory.getLogger(this.getClass());

    static  long latestInsertedAccountId=0;

    //SQL Queries
    private static final String CREATE_TABLE_ACCOUNT="CREATE TABLE IF NOT EXISTS Account(account_id int IDENTITY primary key , first_name varchar(255), last_name  varchar(255) , user_name  varchar(255) , password    varchar(255) , address     varchar(255) , email_id    varchar(255) , phone_number varchar(255))";
    private static final String INSERT_ACCOUNT="insert into account (first_name,last_name,user_name,password,address,email_id,phone_number) values (:first_name,:last_name,:user_name,:password,:address,:email_id,:phone_number) ";
    private static final String SELECT_ALL="Select account_id,first_name,last_name,user_name,password,address,email_id,phone_number from Account";
    private static final String SELECT_BY_ID="Select account_id,first_name,last_name,user_name,password,address,email_id,phone_number from Account where account_id = :account_id";
    private static final String UPDATE_ACCOUNT="Update Account set first_name= :first_name , last_name = :last_name , user_name = :user_name ,password = :password , address = :address ,email_id = :email_id ,phone_number = :phone_number where account_id = :account_id ";
    private static final String DELETE_ACCOUNT="Delete Account where account_id = :account_id";


    public void createTable(){

        this.jdbcTemplate.update(CREATE_TABLE_ACCOUNT);
        log.info("User table created ");
    }

    public void insertAccount(Account account) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ApplicationConstants.FIRST_NAME, account.getFirstName())
                .addValue(ApplicationConstants.LAST_NAME, account.getLastName())
                .addValue(ApplicationConstants.USER_NAME, account.getUserName())
                .addValue(ApplicationConstants.PASSWORD, account.getPassword())
                .addValue(ApplicationConstants.ADDRESS, account.getAddress())
                .addValue(ApplicationConstants.EMAIL_ID, account.getEmailId())
                .addValue(ApplicationConstants.PHONE_NUMBER, account.getPhoneNumber());
        this.namedParameterJdbcTemplate.update(INSERT_ACCOUNT, sqlParameterSource);
        latestInsertedAccountId++;
        log.info("A new Account with Account Id {} has been created",latestInsertedAccountId);
    }



    public List<Account> findAllAccounts(){
        log.info("All Accounts have been returned");
        return this.namedParameterJdbcTemplate.query(SELECT_ALL,AccountRowMapper.INSTANCE);
    }

    public Account findByAccountId(Integer id){

        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue(ApplicationConstants.ACCOUNT_ID,id);

        Account account=this.namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID,sqlParameterSource,AccountRowMapper.INSTANCE);
        log.info("Account with Account Id {} has been found",id);
        return account;
    }

    public void updateAccount(Account account){
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue(ApplicationConstants.ACCOUNT_ID,account.getAccountId())
                .addValue(ApplicationConstants.FIRST_NAME, account.getFirstName())
                .addValue(ApplicationConstants.LAST_NAME, account.getLastName())
                .addValue(ApplicationConstants.USER_NAME, account.getUserName())
                .addValue(ApplicationConstants.PASSWORD, account.getPassword())
                .addValue(ApplicationConstants.ADDRESS, account.getAddress())
                .addValue(ApplicationConstants.EMAIL_ID, account.getEmailId())
                .addValue(ApplicationConstants.PHONE_NUMBER, account.getPhoneNumber());
        int rowUpdated=this.namedParameterJdbcTemplate.update(UPDATE_ACCOUNT,sqlParameterSource);
        if(rowUpdated==0){
            log.info("Account with Account Id {} is not present",account.getAccountId());
            throw new AccountNotFoundException("Account with given Account Id is not present");
        }
        log.info("Account with Account Id {} has been updated",account.getAccountId());
    }

    public void deleteAccountById(Integer id){
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue(ApplicationConstants.ACCOUNT_ID,id);
        int rowsDeleted=this.namedParameterJdbcTemplate.update(DELETE_ACCOUNT,sqlParameterSource);
        if(rowsDeleted==0){
            log.info("No Account with Account Id {} is found",id);
            throw new AccountNotFoundException("No Account with Given Account Id is found");
        }

        log.info("Account with Account Id {} has been Deleted", id);

    }


}
