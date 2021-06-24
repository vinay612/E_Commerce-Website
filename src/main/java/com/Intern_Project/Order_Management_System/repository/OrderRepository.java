package com.Intern_Project.Order_Management_System.repository;

import java.util.List;
import com.Intern_Project.Order_Management_System.model.Order;

public interface OrderRepository {
    //get list of all the orders.
    int createTable();

    List<Order> findAll();

    //get Order details of a particular user.
    List<Order> findOrderDetailsByUserId(int id);

    //create order
    int insertOrder(Order order);

    //get order by order id
    Order findOrderById(int id);

    void insertOrderFromCart(List<Order> orderList);
}
