package com.Intern_Project.Order_Management_System.util.RowMapper;

import com.Intern_Project.Order_Management_System.model.CartItem;
import com.Intern_Project.Order_Management_System.util.ApplicationConstants;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public enum CartItemRowMapper implements RowMapper<CartItem> {

    INSTANCE;

    @Override
    public CartItem mapRow(ResultSet resultSet, int i) throws SQLException {
        CartItem cartItem=new CartItem();
        cartItem.setItemId(resultSet.getInt(ApplicationConstants.CART_ITEM_ID));
        cartItem.setCartId(resultSet.getInt(ApplicationConstants.CART_ID));
        cartItem.setProductId(resultSet.getInt(ApplicationConstants.PRODUCT_ID));
        cartItem.setQuantity(resultSet.getInt(ApplicationConstants.QUANTITY));
        cartItem.setTotalPrice(resultSet.getDouble(ApplicationConstants.TOTAL_PRICE));
        return cartItem;
    }
}
