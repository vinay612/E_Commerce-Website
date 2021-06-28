package com.Intern_Project.Order_Management_System.repository.Impl;

import com.Intern_Project.Order_Management_System.model.Order;
import com.Intern_Project.Order_Management_System.model.OrderItem;
import com.Intern_Project.Order_Management_System.repository.OrderItemRepository;
import com.Intern_Project.Order_Management_System.util.Status;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(new JdbcTemplate());

    private Logger log= LoggerFactory.getLogger(this.getClass());

    private static final String CREATE_TABLE="CREATE Table IF NOT EXISTS OrderItem (id int IDENTITY(100,1) PRIMARY KEY,order_Id int,product_Id int,quantity int,price double,FOREIGN KEY(order_Id) references Orders(order_Id),FOREIGN KEY(product_Id) references Product(product_Id))";
    private static final String INSERT_ORDER_ITEM="INSERT INTO OrderItem(order_Id,product_Id,quantity,price) VALUES(:order_Id,:product_Id,:quantity,:price)";


    @Override
    public void createTable(){
        int rows= this.jdbcTemplate.update(CREATE_TABLE);
        log.info("OrderItem table has been created");
    }


    public void addOrderItem(List<OrderItem> orderItemList){

        MapSqlParameterSource[] mapSqlParameterSource=orderItemList.stream()
                .map(orderItem -> new MapSqlParameterSource()
                        .addValue("order_Id",orderItem.getOrderId())
                        .addValue("product_Id",orderItem.getProductId())
                        .addValue("quantity",orderItem.getQuantity())
                        .addValue("price",orderItem.getPrice()))
                .collect(Collectors.toList()).toArray(new MapSqlParameterSource[]{});
        this.namedParameterJdbcTemplate.batchUpdate(INSERT_ORDER_ITEM,mapSqlParameterSource);
        return;
    }
}
