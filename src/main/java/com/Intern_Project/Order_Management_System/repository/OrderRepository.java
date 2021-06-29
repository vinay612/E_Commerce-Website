package com.Intern_Project.Order_Management_System.repository;

import java.util.List;
import com.Intern_Project.Order_Management_System.model.Order;
import com.Intern_Project.Order_Management_System.model.OrderItem;

public interface OrderRepository {
    //get list of all the orders.
    void createTable();

    //find all Orders
    List<Order> findAllOrders();

    //get Order details of a particular user.
    List<Order> findOrderDetailsByUserId(int id);

    //create order
    void insertOrder(Order order);

    //get order by order id
    List<OrderItem> findOrderById(int id);


    Order findMaximumOrderIdForAccountId(Integer id);

    //delete Order
    void deleteOrder(int id);
}
