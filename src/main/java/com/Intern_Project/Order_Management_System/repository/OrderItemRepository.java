package com.Intern_Project.Order_Management_System.repository;

import com.Intern_Project.Order_Management_System.model.OrderItem;

import java.util.List;

public interface OrderItemRepository {

    //create Table
    void createTable();

    //post OrderItem
    void insertOrderItem(List<OrderItem> orderItemList);

    //delete OrderItem
    void deleteOrderItemById(int id);
}
