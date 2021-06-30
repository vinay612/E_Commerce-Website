package com.Intern_Project.Order_Management_System.repository.Impl;

import com.Intern_Project.Order_Management_System.model.Cart;
import com.Intern_Project.Order_Management_System.model.CartItem;
import com.Intern_Project.Order_Management_System.repository.CartItemRepository;
import com.Intern_Project.Order_Management_System.service.CartService;
import com.Intern_Project.Order_Management_System.service.ProductService;
import com.Intern_Project.Order_Management_System.util.ApplicationConstants;
import com.Intern_Project.Order_Management_System.util.RowMapper.CartItemRowMapper;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.jdbc.Sql;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
@NoArgsConstructor
public class CartItemRepositoryImpl implements CartItemRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());



    //SQL Queries
    private static final String CREATE_TABLE_CART_ITEM="CREATE TABLE IF NOT EXISTS CartItem (item_id int IDENTITY(100,1) primary key,cart_id int,product_id int,quantity int,total_price double,FOREIGN KEY(product_id) REFERENCES Product(product_Id),FOREIGN KEY(cart_id) REFERENCES Cart(cart_id))";
    private static final String INSERT_CART="Insert into CartItem (cart_id,product_id,quantity,total_price) values (:cart_id,:product_id,:quantity,:total_price)";
    private static final String SELECT_BY_CART_ID="Select item_id,cart_id,product_id,quantity,total_price from CartItem where cart_id=:cart_id";
    private static final String SELECT_BY_ITEM_ID="Select item_id,cart_id,product_id,quantity,total_price from CartItem where item_id=:item_id";
    private static final String UPDATE_CART_ITEM="Update CartItem set quantity=:quantity , total_price= :total_price where item_id=:item_id";
    private static final String DELETE_CART_ITEM_BY_ID="Delete from CartItem where item_id=:item_id";
    private static final String DELETE_CART_ITEM_BY_CART_ID="Delete from CartItem where cart_id=:cart_id";
    @Override
    public void createTable(){
        this.jdbcTemplate.update(CREATE_TABLE_CART_ITEM);
        log.info("CartItem Table has been created");
    }

    @Override
    public void insertCartItem(CartItem cartItem){

        double totalPrice,price = productService.getProductById(cartItem.getProductId()).getPrice();
        totalPrice=cartItem.getQuantity()*price;
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue(ApplicationConstants.CART_ID,cartItem.getCartId())
                .addValue(ApplicationConstants.PRODUCT_ID,cartItem.getProductId())
                .addValue(ApplicationConstants.QUANTITY,cartItem.getQuantity())
                .addValue(ApplicationConstants.TOTAL_PRICE,totalPrice);

        this.namedParameterJdbcTemplate.update(INSERT_CART,sqlParameterSource);
        log.info("CartItem Row inserted in cartItem Table ");

        //Updating Total Price in Cart
        cartService.updateCart(cartItem.getCartId());
    }

    @Override
    public List<CartItem> findCartItemsByCartId(Integer id){
        log.info("{}  ",id);
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue(ApplicationConstants.CART_ID,id);
        return this.namedParameterJdbcTemplate.query(SELECT_BY_CART_ID,sqlParameterSource, CartItemRowMapper.INSTANCE);

    }

    public CartItem findItemByItemId(Integer id){
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue(ApplicationConstants.CART_ITEM_ID,id);
        return this.namedParameterJdbcTemplate.queryForObject(SELECT_BY_ITEM_ID,sqlParameterSource,CartItemRowMapper.INSTANCE);
    }

    @Override
    public void updateCartItemById(@NotNull CartItem cartItem){

        double price = productService.getProductById(cartItem.getProductId()).getPrice();

        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue(ApplicationConstants.CART_ITEM_ID,cartItem.getItemId())
                .addValue(ApplicationConstants.QUANTITY,cartItem.getQuantity())
                .addValue(ApplicationConstants.TOTAL_PRICE,price*cartItem.getQuantity());

        this.namedParameterJdbcTemplate.update(UPDATE_CART_ITEM,sqlParameterSource);
        log.info("CartItem with id : {} has been updated",cartItem.getItemId());

        cartService.updateCart(cartItem.getCartId());
    }

    @Override
    public void deleteCartItemByItemId(Integer id){
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue(ApplicationConstants.CART_ITEM_ID,id);
        int cartId=findItemByItemId(id).getCartId();
        this.namedParameterJdbcTemplate.update(DELETE_CART_ITEM_BY_ID,sqlParameterSource);
        log.info("CartItem with id {} has been deleted",id);

        cartService.updateCart(cartId);
    }

    @Override
    public void deleteCartItemByCartId(Integer id){

        log.info("Cart of Account with ID {} has been made empty",id);
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue(ApplicationConstants.CART_ID,id);
        this.namedParameterJdbcTemplate.update(DELETE_CART_ITEM_BY_CART_ID,sqlParameterSource);
        cartService.updateCart(id);
    }
}
