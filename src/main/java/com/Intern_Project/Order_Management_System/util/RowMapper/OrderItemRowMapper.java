package com.Intern_Project.Order_Management_System.util.RowMapper;

import com.Intern_Project.Order_Management_System.model.OrderItem;
import com.Intern_Project.Order_Management_System.util.ApplicationConstants;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public enum OrderItemRowMapper implements RowMapper<OrderItem> {

    INSTANCE;

    @Override
    public OrderItem mapRow(ResultSet resultSet, int i) throws SQLException {
        OrderItem orderItem=new OrderItem();
        orderItem.setItemId(resultSet.getInt(ApplicationConstants.ITEM_ID));
        orderItem.setOrderId(resultSet.getInt(ApplicationConstants.ORDER_ID));
        orderItem.setProductId(resultSet.getInt(ApplicationConstants.PRODUCT_ID));
        orderItem.setQuantity(resultSet.getInt(ApplicationConstants.QUANTITY));
        orderItem.setPrice(resultSet.getDouble(ApplicationConstants.PRICE));
        return orderItem;
    }
}
