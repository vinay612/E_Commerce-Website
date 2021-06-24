package com.Intern_Project.Order_Management_System.repository;

import com.Intern_Project.Order_Management_System.model.OrderItem;

public interface OrderItemRepository {

    //create Table
    int createTable();

    //post OrderItem
    int addOrderItem(OrderItem orderItem);
}
