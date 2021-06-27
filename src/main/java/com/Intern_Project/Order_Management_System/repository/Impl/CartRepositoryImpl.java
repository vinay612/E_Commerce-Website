package com.Intern_Project.Order_Management_System.repository.Impl;

import com.Intern_Project.Order_Management_System.model.Cart;
import com.Intern_Project.Order_Management_System.util.RowMapper.CartRowMapper;
import com.Intern_Project.Order_Management_System.repository.CartRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
@Repository("cartRepository")
public class CartRepositoryImpl implements CartRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedTemplate=new NamedParameterJdbcTemplate(new JdbcTemplate());

    private static final String CREATE_TABLE="CREATE TABLE IF NOT EXISTS Cart (id int IDENTITY(100,1) primary key,account_id int,product_id int,quantity int,total_price double)";
    private static final String INSERT_CART="Insert into Cart (account_id,product_id,quantity,total_price) values (:account_id,:product_id,:quantity,:total_price)";
    private static final String GET_CART_DETAILS_BY_ACCOUNT_ID="Select id,account_id,product_id,quantity,total_price from Cart where account_id=:id";
    private static final String UPDATE_CART="Update Cart set quantity=:quantity where id=:id";
    private static final String DELETE_CART="Delete from Cart where id=:id";

    /*
    Map<String,Object> paramMap=new HashMap<>();
    public void setParamMap(Cart cart){

        paramMap.put("id",cart.getId());
        paramMap.put("account_id",cart.getAccountId());
        paramMap.put("product_id",cart.getProductId());
        paramMap.put("quantity",cart.getQuantity());
        paramMap.put("total_price",cart.getTotalPrice());
        return;

    }

     */

    public void createTable(){

        //String query="CREATE TABLE IF NOT EXISTS Cart (id int IDENTITY(100,1) primary key,account_id int,product_id int,quantity int,total_price double)";
        this.jdbcTemplate.update(CREATE_TABLE);
        System.out.println("Cart table created ");
    }

    public Cart insertCart(Cart cart){

        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue("id",cart.getId())
                .addValue("account_id",cart.getAccountId())
                .addValue("product_id",cart.getProductId())
                .addValue("quantity",cart.getQuantity())
                .addValue("total_price",cart.getTotalPrice());
        //String query="Insert into Cart (account_id,product_id,quantity,total_price) values (:account_id,:product_id,:quantity,:total_price)";
        int count=this.namedTemplate.update(INSERT_CART,sqlParameterSource);

        System.out.println("Row inserted in Cart Table "+count);
        return cart;
    }

    public List<Cart> findByAccountId(Integer id){
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue("id",id);
        //String query="Select id,account_id,product_id,quantity,total_price from Cart where account_id=:id";
        List<Cart> carts=this.namedTemplate.query(GET_CART_DETAILS_BY_ACCOUNT_ID,sqlParameterSource,CartRowMapper.INSTANCE);
        return carts;
    }

    public Cart updateCart(Cart cart){
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue("id",cart.getId())
                .addValue("account_id",cart.getAccountId())
                .addValue("product_id",cart.getProductId())
                .addValue("quantity",cart.getQuantity())
                .addValue("total_price",cart.getTotalPrice());
        //String query="Update Cart set quantity=:quantity where id=:id";
        this.namedTemplate.update(UPDATE_CART,sqlParameterSource);
        return cart;
    }

    public void deleteById(Integer id){
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue("id",id);
        //String query="Delete from Cart where id=:id";
        this.namedTemplate.update(DELETE_CART,sqlParameterSource);
    }

    public void deleteAll(List<Cart> cartList){

        String query="Delete from Cart where id=:id";
        MapSqlParameterSource[] mapSqlParameterSource=cartList.stream()
                .map(cart -> new MapSqlParameterSource()
                        .addValue("id",cart.getId()))
                .collect(Collectors.toList()).toArray(new MapSqlParameterSource[]{});
        this.namedTemplate.batchUpdate(query,mapSqlParameterSource);
    }
}
