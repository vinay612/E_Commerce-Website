package com.Intern_Project.Order_Management_System.repository.Impl;

import com.Intern_Project.Order_Management_System.repository.ProductRepository;
import com.Intern_Project.Order_Management_System.util.ApplicationConstants;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.Intern_Project.Order_Management_System.model.Product;
import com.Intern_Project.Order_Management_System.util.RowMapper.ProductRowMapper;
import sun.java2d.pipe.AlphaPaintPipe;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    Logger log= LoggerFactory.getLogger(this.getClass());

    private static final String CREATE_TABLE="CREATE TABLE IF NOT EXISTS Product(product_Id int IDENTITY(1000,1) NOT NULL PRIMARY KEY,name varchar(30) NOT NULL , price double NOT NULL, description varchar(50),expiry_Date varchar(12) , min_Quantity int)";
    private static final String INSERT_PRODUCT="INSERT INTO PRODUCT(NAME,PRICE,DESCRIPTION,EXPIRY_DATE,MIN_QUANTITY) VALUES (:name,:price,:description,:expiry_Date,:min_Quantity)";
    private static final String GET_ALL_PRODUCTS="SELECT product_Id,name,price,description,expiry_Date,min_Quantity from Product";
    private static final String GET_PRODUCT_BY_ID="SELECT product_Id,name,price,description,expiry_Date,min_Quantity from Product where product_Id=:product_Id";
    private static final String GET_PRODUCT_BY_NAME="SELECT product_Id,name,price,description,expiry_Date,min_Quantity from Product where name=:name";
    private static final String UPDATE_PRODUCT="UPDATE Product set name=:name,price=:price,description=:description,expiry_Date=:expiry_Date,min_Quantity=:min_Quantity where product_Id=:product_Id";

    @Override
    public void createTable()
    {
        this.jdbcTemplate.update(CREATE_TABLE);
        log.info("Product Table has been created");
    }

    @Override
    public List<Product> findAllProducts() {
        return namedParameterJdbcTemplate.query(GET_ALL_PRODUCTS, ProductRowMapper.INSTANCE);

    }

    @Override
    public Product findProductById(int id) {
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource().addValue(ApplicationConstants.PRODUCT_PRODUCTID,id);
        return namedParameterJdbcTemplate.queryForObject(GET_PRODUCT_BY_ID,sqlParameterSource,ProductRowMapper.INSTANCE);
    }

    @Override
    public Product findProductByName(String name) {
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource().addValue(ApplicationConstants.NAME,name);
        return namedParameterJdbcTemplate.queryForObject(GET_PRODUCT_BY_NAME,sqlParameterSource,ProductRowMapper.INSTANCE);
    }

    @Override
    public void insertBatch(List<Product> productList) {
        MapSqlParameterSource[] mapSqlParameterSource=productList.stream()
                .map(product -> new MapSqlParameterSource()
                        .addValue(ApplicationConstants.NAME,product.getName())
                        .addValue(ApplicationConstants.PRICE,product.getPrice())
                        .addValue(ApplicationConstants.DESCRIPTION,product.getDescription())
                        .addValue(ApplicationConstants.EXPIRY_DATE,product.getExpiryDate())
                .addValue(ApplicationConstants.MIN_QUANTITY,product.getMinQuantity()))
                .collect(Collectors.toList()).toArray(new MapSqlParameterSource[]{});
        this.namedParameterJdbcTemplate.batchUpdate(INSERT_PRODUCT,mapSqlParameterSource);
    }

    @Override
    public void updateProductById(Product product) {
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue(ApplicationConstants.PRODUCT_PRODUCTID,product.getProductId())
                .addValue(ApplicationConstants.NAME,product.getName())
                .addValue(ApplicationConstants.PRICE,product.getPrice())
                .addValue(ApplicationConstants.DESCRIPTION,product.getDescription())
                .addValue(ApplicationConstants.EXPIRY_DATE,product.getExpiryDate())
                .addValue(ApplicationConstants.MIN_QUANTITY,product.getMinQuantity());
        this.namedParameterJdbcTemplate.update(UPDATE_PRODUCT,sqlParameterSource);
        log.info("Product with product id {} has been updated",product.getProductId());

    }
}
