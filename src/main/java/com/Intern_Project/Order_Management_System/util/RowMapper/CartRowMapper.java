package com.Intern_Project.Order_Management_System.util.RowMapper;

import com.Intern_Project.Order_Management_System.model.Cart;
import com.Intern_Project.Order_Management_System.util.ApplicationConstants;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public enum CartRowMapper implements RowMapper<Cart> {

    INSTANCE;

    @Override
    public Cart mapRow(ResultSet resultSet, int i) throws SQLException {
        Cart cart=new Cart();
        cart.setCartId(resultSet.getInt(ApplicationConstants.CART_ID));
        cart.setAccountId(resultSet.getInt(ApplicationConstants.ACCOUNT_ID));
        cart.setProductId(resultSet.getInt(ApplicationConstants.PRODUCT_ID));
        cart.setQuantity(resultSet.getInt(ApplicationConstants.QUANTITY));
        cart.setTotalPrice(resultSet.getDouble(ApplicationConstants.TOTAL_PRICE));
        return cart;
    }
}
