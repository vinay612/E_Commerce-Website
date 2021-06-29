package com.Intern_Project.Order_Management_System.util.RowMapper;

import com.Intern_Project.Order_Management_System.model.Account;
import com.Intern_Project.Order_Management_System.util.ApplicationConstants;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public enum AccountRowMapper implements RowMapper<Account> {
    INSTANCE;

    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account=new Account();
        account.setAccountId(resultSet.getInt(ApplicationConstants.ACCOUNT_ID));
        account.setFirstName(resultSet.getString(ApplicationConstants.FIRST_NAME));
        account.setLastName(resultSet.getString(ApplicationConstants.LAST_NAME));
        account.setUserName(resultSet.getString(ApplicationConstants.USER_NAME));
        account.setPassword(resultSet.getString(ApplicationConstants.PASSWORD));
        account.setAddress(resultSet.getString(ApplicationConstants.ADDRESS));
        account.setEmailId(resultSet.getString(ApplicationConstants.EMAIL_ID));
        account.setPhoneNumber(resultSet.getLong(ApplicationConstants.PHONE_NUMBER));
        return account;
    }

}
