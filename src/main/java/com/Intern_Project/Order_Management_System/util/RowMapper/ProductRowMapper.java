package com.Intern_Project.Order_Management_System.util.RowMapper;

import com.Intern_Project.Order_Management_System.model.Product;
import com.Intern_Project.Order_Management_System.util.ApplicationConstants;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public enum ProductRowMapper implements RowMapper<Product> {

    INSTANCE;

    public Product mapRow(ResultSet rs , int row) throws SQLException {

        Product product=new Product();
        product.setProductId(rs.getInt(ApplicationConstants.PRODUCT_ID));
        product.setName(rs.getString(ApplicationConstants.NAME));
        product.setPrice(rs.getDouble(ApplicationConstants.PRICE));
        product.setDescription(rs.getString(ApplicationConstants.DESCRIPTION));
        product.setExpiryDate(rs.getString(ApplicationConstants.EXPIRY_DATE));
        product.setMinQuantity(rs.getInt(ApplicationConstants.MIN_QUANTITY));
        return product;

    }
}
