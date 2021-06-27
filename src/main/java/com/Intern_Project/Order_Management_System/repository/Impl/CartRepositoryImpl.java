package com.Intern_Project.Order_Management_System.repository.Impl;

import com.Intern_Project.Order_Management_System.model.Cart;
import com.Intern_Project.Order_Management_System.util.RowMapper.CartRowMapper;
import com.Intern_Project.Order_Management_System.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


@Repository("cartRepository")
public class CartRepositoryImpl implements CartRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    //SQL Queries
    private static final String CREATE_TABLE="CREATE TABLE IF NOT EXISTS Cart (id int IDENTITY(100,1) primary key,account_id int,product_id int,quantity int,total_price double)";
    private static final String INSERT_CART="Insert into Cart (account_id,product_id,quantity,total_price) values (:account_id,:product_id,:quantity,:total_price)";
    private static final String SELECT_BY_ACCOUNT_ID="Select * from Cart where account_id=:id";
    private static final String UPDATE_CART="Update Cart set quantity=:quantity where id=:id";
    private static final String DELETE_CART_BY_ID="Delete from Cart where id=:id";
    public void createTable(){

        this.jdbcTemplate.update(CREATE_TABLE);
        log.info("Cart Table has been created");
    }

    public void insertCart(Cart cart){

        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue("account_id",cart.getAccountId())
                .addValue("product_id",cart.getProductId())
                .addValue("quantity",cart.getQuantity())
                .addValue("total_price",cart.getTotalPrice());

        int count=this.namedParameterJdbcTemplate.update(INSERT_CART,sqlParameterSource);

        log.info("Number of Rows inserted in cart Table is : {}",count);
        return ;
    }

    public List<Cart> findCartByAccountId(Integer id){
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue("id",id);
        List<Cart> carts=this.namedParameterJdbcTemplate.query(SELECT_BY_ACCOUNT_ID,sqlParameterSource,new CartRowMapper());
        return carts;
    }

    public void updateCartById(@NotNull Cart cart){
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue("id",cart.getId())
                .addValue("account_id",cart.getAccountId())
                .addValue("product_id",cart.getProductId())
                .addValue("quantity",cart.getQuantity())
                .addValue("total_price",cart.getTotalPrice());

        this.namedParameterJdbcTemplate.update(UPDATE_CART,sqlParameterSource);
        log.info("Cart with id : {} has been updated",cart.getId());
        return ;
    }

    public void deleteCartByCartId(Integer id){
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue("id",id);

        this.namedParameterJdbcTemplate.update(DELETE_CART_BY_ID,sqlParameterSource);
        log.info("Cart with id {} has been deleted",id);
    }

    public void deleteCartByAccountId(List<Cart> cartList){

        log.info("Cart of Account with ID {} has been made empty",cartList.get(0).getAccountId());
        MapSqlParameterSource[] mapSqlParameterSource=cartList.stream()
                .map(cart -> new MapSqlParameterSource()
                        .addValue("id",cart.getId()))
                .collect(Collectors.toList()).toArray(new MapSqlParameterSource[]{});
        this.namedParameterJdbcTemplate.batchUpdate(DELETE_CART_BY_ID,mapSqlParameterSource);
    }
}
