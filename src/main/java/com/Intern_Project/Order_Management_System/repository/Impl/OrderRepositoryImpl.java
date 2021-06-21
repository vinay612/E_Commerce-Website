package com.Intern_Project.Order_Management_System.repository.Impl;

import com.Intern_Project.Order_Management_System.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.Intern_Project.Order_Management_System.model.Order;
import com.Intern_Project.Order_Management_System.util.RowMapper.OrderRowMapper;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public OrderRepositoryImpl()
    {

    }

    // Creating table Product
    public int createTable()
    {
        String query="CREATE TABLE IF NOT EXISTS Orders(order_Id int PRIMARY KEY,user_Id int NOT NULL, product_Id int NOT NULL ,quantity int NOT NULL, purchase_Date varchar(12) ,purchase_Time varchar(12), total_Price double NOT NULL, FOREIGN KEY(product_Id) references Product(product_Id))";
        int update=this.jdbcTemplate.update(query);
        return update;
    }

    @Override
    public List<Order> findAll() {
        String query="SELECT order_Id,user_Id,product_Id,quantity,purchase_Date,purchase_Time,total_Price from Orders";
        List<Order> allOrders = namedParameterJdbcTemplate.query(query, new OrderRowMapper());
        return allOrders;
    }

    @Override
    public List<Order> findOrderDetailsByUserId(int id) {

        SqlParameterSource sqlParameterSource=new MapSqlParameterSource().addValue("user_Id",id);
        String query="SELECT order_Id,user_Id,product_Id,quantity,purchase_Date,purchase_Time,total_Price from Orders where user_Id=:user_Id";
        // List<Order> userOrders = namedParameterJdbcTemplate.query(query, new OrderRowMapper());
        List<Order> userOrders=namedParameterJdbcTemplate.query(query,sqlParameterSource,new OrderRowMapper());
        return userOrders;
    }

    @Override
    public int insertOrder(Order order) {

        String query="INSERT INTO ORDERS(order_Id,user_Id,product_Id,quantity,purchase_Date,purchase_Time,total_Price) VALUES(:order_Id,:user_Id,:product_Id,:quantity,:purchase_Date,:purchase_Time,:total_Price)";
        MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue("order_Id",order.getOrderId())
                .addValue("user_Id",order.getUserId())
                .addValue("product_Id",order.getProductId())
                .addValue("quantity",order.getQuantity())
                .addValue("purchase_Date",order.getPurchaseDate())
                .addValue("purchase_Time",order.getPurchaseTime())
                .addValue("total_Price",order.getTotalPrice());

        System.out.println(order.getPurchaseDate()+" "+order.getPurchaseTime());

        int rows=namedParameterJdbcTemplate.update(query,mapSqlParameterSource);
        return rows;
    }

    @Override
    public Order findOrderById(int id) {

        SqlParameterSource sqlParameterSource=new MapSqlParameterSource().addValue("order_Id",id);
        String query="SELECT order_Id,user_Id,product_Id,quantity,purchase_Date,purchase_Time,total_Price from Orders where order_Id=:order_Id";
        return namedParameterJdbcTemplate.queryForObject(query,sqlParameterSource,new OrderRowMapper());
    }

}
