package com.Intern_Project.Order_Management_System.util.RowMapper;

import com.Intern_Project.Order_Management_System.model.Product;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public enum ProductRowMapper implements RowMapper<Product> {

    INSTANCE;

    public Product mapRow(ResultSet rs , int row) throws SQLException {

        Product pm=new Product();
        pm.setProductId(rs.getInt("product_Id"));
        pm.setName(rs.getString("name"));
        pm.setPrice(rs.getDouble("price"));
        pm.setDescription(rs.getString("description"));
        pm.setExpiryDate(rs.getString("expiry_Date"));
        pm.setMinQuantity(rs.getInt("min_Quantity"));
        return pm;

    }
}
