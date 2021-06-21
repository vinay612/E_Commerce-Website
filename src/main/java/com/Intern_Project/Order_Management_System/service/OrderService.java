package com.Intern_Project.Order_Management_System.service;

import java.util.List;
import com.Intern_Project.Order_Management_System.model.Order;

public interface OrderService {

    // add a new Order.
    int insertOrder(Order order);

    // get all the Orders.
    List<Order> findAll();

    // get details of a particular order by id.
    Order findOrderById(int id);

    // get details of all orders of a particular user.
    List<Order> findOrderDetailsByUserId(int id);
}
