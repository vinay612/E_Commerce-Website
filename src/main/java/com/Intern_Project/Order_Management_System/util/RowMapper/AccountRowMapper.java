package com.Intern_Project.Order_Management_System.util.RowMapper;

import com.Intern_Project.Order_Management_System.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account=new Account();
        account.setAccountId(resultSet.getInt("account_id"));
        account.setFirstName(resultSet.getString("first_name"));
        account.setLastName(resultSet.getString("last_name"));
        account.setUserName(resultSet.getString("user_name"));
        account.setPassword(resultSet.getString("password"));
        account.setAddress(resultSet.getString("address"));
        account.setEmailId(resultSet.getString("email_id"));
        account.setPhoneNumber(resultSet.getString("phone_number"));
        return account;
    }

}
