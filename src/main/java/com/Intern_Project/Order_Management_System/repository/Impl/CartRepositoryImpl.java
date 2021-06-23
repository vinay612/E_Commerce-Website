package com.Intern_Project.Order_Management_System.repository.Impl;

import com.Intern_Project.Order_Management_System.model.Cart;
import com.Intern_Project.Order_Management_System.util.RowMapper.CartRowMapper;
import com.Intern_Project.Order_Management_System.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository("cartRepository")
public class CartRepositoryImpl implements CartRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedTemplate=new NamedParameterJdbcTemplate(new JdbcTemplate());

    Map<String,Object> paramMap=new HashMap<>();
    public void setParamMap(Cart cart){

        paramMap.put("id",cart.getId());
        paramMap.put("account_id",cart.getAccountId());
        paramMap.put("product_id",cart.getProductId());
        paramMap.put("quantity",cart.getQuantity());
        paramMap.put("total_price",cart.getTotalPrice());
        return;

    }

    public void createTable(){

        String query="CREATE TABLE IF NOT EXISTS Cart (id int IDENTITY(100,1) primary key,account_id int,product_id int,quantity int,total_price double)";
        this.jdbcTemplate.update(query);
        System.out.println("Cart table created ");
    }

    public Cart insertCart(Cart cart){

        setParamMap(cart);
        String query="Insert into Cart (account_id,product_id,quantity,total_price) values (:account_id,:product_id,:quantity,:total_price)";
        int count=this.namedTemplate.update(query,paramMap);
        System.out.println("Row inserted in Cart Table "+count);
        return cart;
    }

    public List<Cart> findByAccountId(Integer id){
        paramMap.put("id",id);
        String query="Select id,account_id,product_id,quantity,total_price from Cart where account_id=:id";
        List<Cart> carts=this.namedTemplate.query(query,paramMap,new CartRowMapper());
        return carts;
    }

    public Cart updateCart(Cart cart){
        setParamMap(cart);
        String query="Update Cart set quantity=:quantity where id=:id";
        this.namedTemplate.update(query,paramMap);
        return cart;
    }
    public void deleteById(Integer id){
        paramMap.put("id",id);
        String query="Delete from Cart where id=:id";
        this.namedTemplate.update(query,paramMap);
    }
}
