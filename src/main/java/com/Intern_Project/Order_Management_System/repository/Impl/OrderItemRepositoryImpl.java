package com.Intern_Project.Order_Management_System.repository.Impl;

import com.Intern_Project.Order_Management_System.model.Order;
import com.Intern_Project.Order_Management_System.model.OrderItem;
import com.Intern_Project.Order_Management_System.repository.OrderItemRepository;
import com.Intern_Project.Order_Management_System.util.ApplicationConstants;
import com.Intern_Project.Order_Management_System.util.Status;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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

    private static final String CREATE_TABLE="CREATE Table IF NOT EXISTS OrderItem (item_Id int IDENTITY(100,1) PRIMARY KEY,order_Id int,product_Id int,quantity int,price double,FOREIGN KEY(order_Id) references Orders(order_Id),FOREIGN KEY(product_Id) references Product(product_Id))";
    private static final String INSERT_ORDER_ITEM="INSERT INTO OrderItem(order_Id,product_Id,quantity,price) VALUES(:order_Id,:product_Id,:quantity,:price)";
    private static final String DELETE_ORDERITEM="DELETE FROM OrderItem where order_Id=:order_Id";


    @Override
    public void createTable(){
        this.jdbcTemplate.update(CREATE_TABLE);
        log.info("OrderItem table has been created");
    }


    public void insertOrderItem(List<OrderItem> orderItemList){

        MapSqlParameterSource[] mapSqlParameterSource=orderItemList.stream()
                .map(orderItem -> new MapSqlParameterSource()
                        .addValue(ApplicationConstants.ORDER_ID,orderItem.getOrderId())
                        .addValue(ApplicationConstants.PRODUCT_PRODUCTID,orderItem.getProductId())
                        .addValue(ApplicationConstants.QUANTITY,orderItem.getQuantity())
                        .addValue(ApplicationConstants.PRICE,orderItem.getPrice()))
                .collect(Collectors.toList()).toArray(new MapSqlParameterSource[]{});
        this.namedParameterJdbcTemplate.batchUpdate(INSERT_ORDER_ITEM,mapSqlParameterSource);
    }

    @Override
    public void deleteOrderItemById(int id) {
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource().addValue(ApplicationConstants.ORDER_ID,id);
        namedParameterJdbcTemplate.update(DELETE_ORDERITEM,sqlParameterSource);
        log.info("OrderItem with order id {} has been deleted",id);
    }
}
