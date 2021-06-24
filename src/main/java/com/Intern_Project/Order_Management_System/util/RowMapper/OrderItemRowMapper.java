package com.Intern_Project.Order_Management_System.util.RowMapper;

import com.Intern_Project.Order_Management_System.model.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper<OrderItem> {

    @Override
    public OrderItem mapRow(ResultSet resultSet, int i) throws SQLException {
        OrderItem orderItem=new OrderItem();
        orderItem.setId(resultSet.getInt("id"));
        orderItem.setOrderId(resultSet.getInt("order_Id"));
        orderItem.setProductId(resultSet.getInt("product_Id"));
        orderItem.setQuantity(resultSet.getInt("quantity"));
        orderItem.setPrice(resultSet.getDouble("price"));
        return orderItem;
    }
}
