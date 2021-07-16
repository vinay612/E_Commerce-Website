package com.Intern_Project.Order_Management_System.repository.Impl;

import com.Intern_Project.Order_Management_System.exception.AccountNotExistException;
import com.Intern_Project.Order_Management_System.model.Cart;
import com.Intern_Project.Order_Management_System.model.CartItem;
import com.Intern_Project.Order_Management_System.service.CartItemService;
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
    private CartItemService cartItemService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());



    //SQL Queries
    private static final String CREATE_TABLE_CART="CREATE TABLE IF NOT EXISTS Cart (cart_id int PRIMARY KEY,total_price double,FOREIGN KEY(cart_id) REFERENCES Account(account_id))";
    private static final String INSERT_CART="Insert into Cart (cart_id,total_price) values (:cart_id,:total_price)";
    private static final String SELECT_CART_BY_ID="Select cart_id,total_price from Cart where cart_id=:cart_id";
    private static final String UPDATE_CART="Update Cart set total_price= :total_price where cart_id=:cart_id";
    private static final String DELETE_CART="Delete from Cart where cart_id=:cart_id";

    public void createTable(){
        this.jdbcTemplate.update(CREATE_TABLE_CART);
        log.info("Cart Table has been created");
    }

    public void insertCart(Integer id){

        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue(ApplicationConstants.CART_ID,id)
                .addValue(ApplicationConstants.TOTAL_PRICE,0);

        this.namedParameterJdbcTemplate.update(INSERT_CART,sqlParameterSource);
        log.info("Cart for Account Id {} inserted in cart Table ",id);
    }

    public Cart getCartById(Integer id){

        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue(ApplicationConstants.CART_ID,id);
        log.info("Cart for Account Id {} is returned from cart Table ",id);
        return this.namedParameterJdbcTemplate.queryForObject(SELECT_CART_BY_ID,sqlParameterSource,CartRowMapper.INSTANCE);
    }
    public void updateCartById(int cartId){

        List<CartItem> cartItemList=cartItemService.findByAccountId(cartId);
        double totalPrice=cartItemList.stream().mapToDouble(CartItem::getTotalPrice).sum();

        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue(ApplicationConstants.CART_ID,cartId)
                .addValue(ApplicationConstants.TOTAL_PRICE,totalPrice);

        this.namedParameterJdbcTemplate.update(UPDATE_CART,sqlParameterSource);
        log.info("Cart with id : {} has been updated",cartId);
    }

    public void deleteCartById(Integer id){
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue(ApplicationConstants.CART_ID,id);
        int rowUpdated=this.namedParameterJdbcTemplate.update(DELETE_CART,sqlParameterSource);
        if(rowUpdated == 0)
            throw new AccountNotExistException("Cart with id "+ id + " does not exist");
        log.info("Cart with id : {} has been deleted",id);
    }
}
