package com.Intern_Project.Order_Management_System.repository.Impl;

import com.Intern_Project.Order_Management_System.repository.ProductRepository;
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
    private static final String BATCH_INSERT="INSERT INTO PRODUCT(NAME,PRICE,DESCRIPTION,EXPIRY_DATE,MIN_QUANTITY) VALUES (?,?,?,?,?)";
    private static final String UPDATE_PRODUCT="UPDATE Product set price=:price where product_Id=:product_Id";
    public static final String CONSTANT_PRODUCT_ID="product_Id";
    public static final String CONSTANT_PRICE="price";
    public static final String CONSTANT_NAME="name";
    public static final String CONSTANT_EXPIRY_DATE="expiry_Date";

    @Override
    public void createTable()
    {
        this.jdbcTemplate.update(CREATE_TABLE);
        log.info("Product Table has been created");
    }

    @Override
    public void insertProduct(Product product) {
        MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name",product.getName())
                .addValue("price",product.getPrice())
                .addValue("description",product.getDescription())
                .addValue("expiry_Date",product.getExpiryDate())
                .addValue("min_Quantity",product.getMinQuantity());
        namedParameterJdbcTemplate.update(INSERT_PRODUCT,mapSqlParameterSource);
        log.info("A new product has been added.");

    }

    @Override
    public List<Product> findAllproducts() {
        return namedParameterJdbcTemplate.query(GET_ALL_PRODUCTS, ProductRowMapper.INSTANCE);

    }

    @Override
    public Product findProductById(int id) {
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource().addValue("product_Id",id);
        return namedParameterJdbcTemplate.queryForObject(GET_PRODUCT_BY_ID,sqlParameterSource,ProductRowMapper.INSTANCE);
    }

    @Override
    public Product findProductByName(String name) {
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource().addValue("name",name);
        return namedParameterJdbcTemplate.queryForObject(GET_PRODUCT_BY_NAME,sqlParameterSource,ProductRowMapper.INSTANCE);
    }

   /* public void insertBatch(List<Product> products){  //todo
         jdbcTemplate.batchUpdate(BATCH_INSERT, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Product product=products.get(i);
                preparedStatement.setString(1,product.getName());
                preparedStatement.setDouble(2,product.getPrice());
                preparedStatement.setString(3,product.getDescription());
                preparedStatement.setString(4,product.getExpiryDate());
                preparedStatement.setInt(5,product.getMinQuantity());

            }

            @Override
            public int getBatchSize() {
                int noProducts= products.size();
                log.info(noProducts+" new products have been added");
                return noProducts;
            }
        });
    }*/

    @Override
    public void insertBatch(List<Product> productList) {
        MapSqlParameterSource[] mapSqlParameterSource=productList.stream()
                .map(product -> new MapSqlParameterSource()
                        .addValue("name",product.getName())
                        .addValue("price",product.getPrice())
                        .addValue("description",product.getDescription())
                        .addValue("expiry_Date",product.getExpiryDate())
                .addValue("min_Quantity",product.getMinQuantity()))
                .collect(Collectors.toList()).toArray(new MapSqlParameterSource[]{});
        this.namedParameterJdbcTemplate.batchUpdate(INSERT_PRODUCT,mapSqlParameterSource);
    }

    @Override
    public void updateProductById(Product product) {
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue(CONSTANT_PRODUCT_ID,product.getProductId())
                .addValue(CONSTANT_PRICE,product.getPrice());
        this.namedParameterJdbcTemplate.update(UPDATE_PRODUCT,sqlParameterSource);
        log.info("Product with product id {} has been updated",product.getProductId());

    }
}
