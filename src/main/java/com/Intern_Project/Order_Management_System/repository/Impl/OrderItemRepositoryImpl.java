package com.Intern_Project.Order_Management_System.repository.Impl;

import com.Intern_Project.Order_Management_System.model.OrderItem;
import com.Intern_Project.Order_Management_System.repository.OrderItemRepository;
import com.Intern_Project.Order_Management_System.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(new JdbcTemplate());

    public void OrderItemRepositoryImpl(){

    }

    @Override
    public int createTable(){
        String query="CREATE Table IF NOT EXISTS OrderItem (id int IDENTITY(100,1) PRIMARY KEY,order_Id int,product_Id int,quantity int,price double)";
       // FOREIGN KEY(order_Id) references Orders(order_Id),FOREIGN KEY(product_Id) references Product(product_Id)
        return this.jdbcTemplate.update(query);
    }

    @Override
    public int addOrderItem(OrderItem orderItem) {
        String query="INSERT INTO OrderItem(order_Id,product_Id,quantity,price) VALUES(:order_Id,:product_Id,:quantity,:price)";
        MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue("order_Id",orderItem.getOrderId())
                .addValue("product_Id",orderItem.getProductId())
                .addValue("quantity",orderItem.getQuantity())
                .addValue("price",orderItem.getPrice());

        //System.out.println(orderItem.getPurchaseDate()+" "+orderItem.getPurchaseTime());
        int rows=namedParameterJdbcTemplate.update(query,mapSqlParameterSource);
        return rows;
    }
}
