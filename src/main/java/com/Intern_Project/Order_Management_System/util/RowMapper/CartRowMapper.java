package com.Intern_Project.Order_Management_System.util.RowMapper;

import com.Intern_Project.Order_Management_System.model.Cart;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public enum CartRowMapper implements RowMapper<Cart> {

    INSTANCE;

    @Override
    public Cart mapRow(ResultSet resultSet, int i) throws SQLException {
        Cart cart=new Cart();
        cart.setId(resultSet.getInt("id"));
        cart.setAccountId(resultSet.getInt("account_id"));
        cart.setProductId(resultSet.getInt("product_id"));
        cart.setQuantity(resultSet.getInt("quantity"));
        cart.setTotalPrice(resultSet.getDouble("total_price"));
        return cart;
    }
}
