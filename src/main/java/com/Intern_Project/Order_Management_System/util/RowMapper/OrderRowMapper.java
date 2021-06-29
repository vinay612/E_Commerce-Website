package com.Intern_Project.Order_Management_System.util.RowMapper;

import com.Intern_Project.Order_Management_System.model.Order;
import com.Intern_Project.Order_Management_System.util.ApplicationConstants;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public enum OrderRowMapper implements RowMapper<Order> {

    INSTANCE;

    @Override
    public Order mapRow(ResultSet rs, int row)throws SQLException {

        Order order=new Order();
        order.setOrderId(rs.getInt(ApplicationConstants.ORDER_ID));
        order.setUserId(rs.getInt(ApplicationConstants.USER_ID));
        order.setPurchaseDate(rs.getString(ApplicationConstants.PURCHASE_DATE));
        order.setPurchaseTime(rs.getString(ApplicationConstants.PURCHASE_TIME));
        order.setTotalPrice(rs.getDouble(ApplicationConstants.TOTAL_PRICE));
        return order;
    }
}
