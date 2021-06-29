package com.Intern_Project.Order_Management_System.service;

import java.util.List;
import com.Intern_Project.Order_Management_System.model.Order;
import com.Intern_Project.Order_Management_System.model.OrderItem;

public interface OrderService {

    // create Table
    void createTable();

    // add a new Order.
    void addOrder(Order order);

    //finds maximum orderId for a Account
    public Order findMaxOrderIdForAccountId(Integer id);

    // get all the Orders.
    List<Order> getAllOrderDetails();

    // get details of a particular order by id.
    List<OrderItem> getOrderById(int id);

    // get details of all orders of a particular user.
    List<Order> getOrderDetailsByUserId(int id);

    //delete a order
    void deleteOrder(int id);
}
