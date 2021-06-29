package com.Intern_Project.Order_Management_System.repository.Impl;

import com.Intern_Project.Order_Management_System.model.Cart;
import com.Intern_Project.Order_Management_System.service.ProductService;
import com.Intern_Project.Order_Management_System.util.ApplicationConstants;
import com.Intern_Project.Order_Management_System.util.RowMapper.CartRowMapper;
import com.Intern_Project.Order_Management_System.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Repository("cartRepository")
public class CartRepositoryImpl implements CartRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ProductService productService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());



    //SQL Queries
    private static final String CREATE_TABLE="CREATE TABLE IF NOT EXISTS Cart (cart_id int IDENTITY(100,1) primary key,account_id int,product_id int,quantity int,total_price double,FOREIGN KEY(account_id) REFERENCES Account(account_id),FOREIGN KEY(product_id) REFERENCES Product(product_Id))";
    private static final String INSERT_CART="Insert into Cart (account_id,product_id,quantity,total_price) values (:account_id,:product_id,:quantity,:total_price)";
    private static final String SELECT_BY_ACCOUNT_ID="Select cart_id,account_id,product_id,quantity,total_price from Cart where account_id=:account_id";
    private static final String UPDATE_CART="Update Cart set quantity=:quantity , total_price= :total_price where cart_id=:cart_id";
    private static final String DELETE_CART_BY_ID="Delete from Cart where cart_id=:cart_id";
    public void createTable(){
        this.jdbcTemplate.update(CREATE_TABLE);
        log.info("Cart Table has been created");
    }

    public void insertCart(Cart cart){

        double price = productService.getProductById(cart.getProductId()).getPrice();
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue(ApplicationConstants.ACCOUNT_ID,cart.getAccountId())
                .addValue(ApplicationConstants.PRODUCT_ID,cart.getProductId())
                .addValue(ApplicationConstants.QUANTITY,cart.getQuantity())
                .addValue(ApplicationConstants.TOTAL_PRICE,cart.getQuantity()*price);

        int count=this.namedParameterJdbcTemplate.update(INSERT_CART,sqlParameterSource);
        log.info("Number of Rows inserted in cart Table is : {}",count);
    }

    public List<Cart> findCartByAccountId(Integer id){
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue(ApplicationConstants.ACCOUNT_ID,id);
        return this.namedParameterJdbcTemplate.query(SELECT_BY_ACCOUNT_ID,sqlParameterSource,CartRowMapper.INSTANCE);

    }

    public void updateCartById(@NotNull Cart cart){

        double price = productService.getProductById(cart.getProductId()).getPrice();
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue(ApplicationConstants.CART_ID,cart.getCartId())
                .addValue(ApplicationConstants.QUANTITY,cart.getQuantity())
                .addValue(ApplicationConstants.TOTAL_PRICE,price*cart.getQuantity());

        this.namedParameterJdbcTemplate.update(UPDATE_CART,sqlParameterSource);
        log.info("Cart with id : {} has been updated",cart.getCartId());
    }

    public void deleteCartByCartId(Integer id){
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue(ApplicationConstants.CART_ID,id);
        this.namedParameterJdbcTemplate.update(DELETE_CART_BY_ID,sqlParameterSource);
        log.info("Cart with id {} has been deleted",id);
    }

    public void deleteCartByAccountId(List<Cart> cartList){

        log.info("Cart of Account with ID {} has been made empty",cartList.get(0).getAccountId());
        MapSqlParameterSource[] mapSqlParameterSource=cartList.stream()
                .map(cart -> new MapSqlParameterSource()
                        .addValue(ApplicationConstants.CART_ID,cart.getCartId()))
                .collect(Collectors.toList()).toArray(new MapSqlParameterSource[]{});
        this.namedParameterJdbcTemplate.batchUpdate(DELETE_CART_BY_ID,mapSqlParameterSource);
    }
}
