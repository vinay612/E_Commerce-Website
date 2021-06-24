package com.Intern_Project.Order_Management_System.util.RowMapper;

import com.Intern_Project.Order_Management_System.model.Order;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int row)throws SQLException {

        Order om=new Order();
        //om.setOrderId(rs.getInt("orderId"));
        om.setOrderId(rs.getInt("order_Id"));
        om.setUserId(rs.getInt("user_Id"));
        //om.setProductId(rs.getInt("product_Id"));
        //om.setQuantity(rs.getInt("quantity"));
        om.setPurchaseDate(rs.getString("purchase_Date"));
        om.setPurchaseTime(rs.getString("purchase_Time"));
        om.setTotalPrice(rs.getDouble("total_Price"));
        return om;
    }
}
