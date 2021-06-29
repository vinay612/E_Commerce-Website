package com.Intern_Project.Order_Management_System.repository.Impl;

import com.Intern_Project.Order_Management_System.repository.OrderRepository;
import com.Intern_Project.Order_Management_System.util.ApplicationConstants;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.Intern_Project.Order_Management_System.model.Order;
import com.Intern_Project.Order_Management_System.util.RowMapper.OrderRowMapper;
import com.Intern_Project.Order_Management_System.util.RowMapper.OrderItemRowMapper;
import com.Intern_Project.Order_Management_System.model.OrderItem;
import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    Logger log= LoggerFactory.getLogger(this.getClass());

    private static final String CREATE_TABLE="CREATE TABLE IF NOT EXISTS Orders(order_Id int IDENTITY(100,1) PRIMARY KEY,user_Id int NOT NULL, purchase_Date varchar(12) ,purchase_Time varchar(12), total_Price double NOT NULL,FOREIGN KEY(user_Id) references Account(account_id))";
    private static final String GET_ALL_ORDERS="SELECT order_Id,user_Id,purchase_Date,purchase_Time,total_Price from Orders";
    private static final String INSERT_ORDER="INSERT INTO ORDERS(user_Id,purchase_Date,purchase_Time,total_Price) VALUES(:user_Id,:purchase_Date,:purchase_Time,:total_Price)";
    private static final String GET_ORDER_DETAILS_BY_USER_ID="SELECT order_Id,user_Id,purchase_Date,purchase_Time,total_Price FROM Orders WHERE user_Id=:user_Id";
    private static final String GET_ORDER_DETAILS_BY_ORDER_ID="SELECT item_Id,order_Id,product_Id,quantity,price FROM OrderItem WHERE order_Id=:order_Id";
    private static final String DELETE_ORDER="DELETE FROM Orders WHERE order_Id=:order_Id";
    private static final String MAX_ORDER_ID_FOR_ACCOUNT_ID="Select * from Orders where user_id=:user_id ORDER BY order_id DESC LIMIT 1";

    // Creating table Product
    public void createTable()
    {
        this.jdbcTemplate.update(CREATE_TABLE);
        log.info("Order Table has been created.");
    }

    @Override
    public List<Order> findAllOrders() {
        return namedParameterJdbcTemplate.query(GET_ALL_ORDERS, OrderRowMapper.INSTANCE);
    }

    @Override
    public List<Order> findOrderDetailsByUserId(int id) {

        SqlParameterSource sqlParameterSource=new MapSqlParameterSource().addValue(ApplicationConstants.USER_ID,id);
        return namedParameterJdbcTemplate.query(GET_ORDER_DETAILS_BY_USER_ID,sqlParameterSource,OrderRowMapper.INSTANCE);

    }

    @Override
    public void insertOrder(Order order) {
        MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue(ApplicationConstants.USER_ID,order.getUserId()) //todo
                .addValue(ApplicationConstants.PURCHASE_DATE,order.getPurchaseDate())
                .addValue(ApplicationConstants.PURCHASE_TIME,order.getPurchaseTime())
                .addValue(ApplicationConstants.ORDER_TOTAL_PRICE,order.getTotalPrice());
        namedParameterJdbcTemplate.update(INSERT_ORDER,mapSqlParameterSource);
        log.info("A new order has been placed.");
    }


    @Override
    public List<OrderItem> findOrderById(int id) {
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource().addValue(ApplicationConstants.ORDER_ID,id);
        return namedParameterJdbcTemplate.query(GET_ORDER_DETAILS_BY_ORDER_ID,sqlParameterSource,OrderItemRowMapper.INSTANCE);

    }

    @Override
    public void deleteOrder(int id) {
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource().addValue(ApplicationConstants.ORDER_ID,id);
        namedParameterJdbcTemplate.update(DELETE_ORDER,sqlParameterSource);
        log.info("Order with order id {} has been deleted.",id);
    }

    public Order findMaximumOrderIdForAccountId(Integer id){
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource().addValue(ApplicationConstants.USER_ID,id);
        return namedParameterJdbcTemplate.queryForObject(MAX_ORDER_ID_FOR_ACCOUNT_ID,sqlParameterSource,OrderRowMapper.INSTANCE);
    }


}
