package com.Intern_Project.Order_Management_System.service;

import java.util.List;
import com.Intern_Project.Order_Management_System.model.Order;
import com.Intern_Project.Order_Management_System.model.OrderItem;

public interface OrderService {

    // create Table
    void createTable();

    // add a new Order.
    int insertOrder(Order order);

    void insertOrderFromCart(List<Order> orderList);

    // get all the Orders.
    List<Order> findAll();

    // get details of a particular order by id.
    List<OrderItem> findOrderById(int id);

    // get details of all orders of a particular user.
    List<Order> findOrderDetailsByUserId(int id);

    //delete a order
    int deleteOrder(int id);
}
