package com.Intern_Project.Order_Management_System.repository.Impl;

import com.Intern_Project.Order_Management_System.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.Intern_Project.Order_Management_System.model.Product;
import com.Intern_Project.Order_Management_System.util.RowMapper.ProductRowMapper;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(){

    }
    // Creating table Product
    public int createTable()
    {
        String query="CREATE TABLE IF NOT EXISTS Product(product_Id int IDENTITY(1000,1) NOT NULL PRIMARY KEY,name varchar(30) NOT NULL , price double NOT NULL, description varchar(50),expiry_Date varchar(12) , min_Quantity int)";
        int update=this.jdbcTemplate.update(query);
        return update;
    }

    // Insert a product.
    @Override
    public int addProduct(Product product) {

        String query="INSERT INTO PRODUCT(NAME,PRICE,DESCRIPTION,EXPIRY_DATE,MIN_QUANTITY) VALUES (:name,:price,:description,:expiry_Date,:min_Quantity)";
        //String query="INSERT INTO PRODUCT(product_Id,name,price,description,expiry_Date,min_Quantity) VALUES (:product_Id,:name,:price,:description,:expiry_Date,:min_Quantity)";
        System.out.println("Product added");
        System.out.println(product.getProductId()+" "+product.getExpiryDate()+" "+product.getMinQuantity());
        MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name",product.getName())
                .addValue("price",product.getPrice())
                .addValue("description",product.getDescription())
                .addValue("expiry_Date",product.getExpiryDate())
                .addValue("min_Quantity",product.getMinQuantity());
        int rows=namedParameterJdbcTemplate.update(query,mapSqlParameterSource);
        return rows;

    }

    @Override
    public List<Product> getAllproducts() {
        String query="SELECT product_Id,name,price,description,expiry_Date,min_Quantity from Product";
        List<Product> allProducts = namedParameterJdbcTemplate.query(query, new ProductRowMapper());
        return allProducts;
    }

    @Override
    public Product getProductById(int id) {
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource().addValue("product_Id",id);
        String query="SELECT product_Id,name,price,description,expiry_Date,min_Quantity from Product where product_Id=:product_Id";
        return namedParameterJdbcTemplate.queryForObject(query,sqlParameterSource,new ProductRowMapper());
    }

    @Override
    public List<Product> sortByProductPrice() {
        return null;
    }

    @Override
    public Product getProductByName(String name) {
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource().addValue("name",name);
        String query="SELECT product_Id,name,price,description,expiry_Date,min_Quantity from Product where name=:name";
        return namedParameterJdbcTemplate.queryForObject(query,sqlParameterSource,new ProductRowMapper());
    }


}
