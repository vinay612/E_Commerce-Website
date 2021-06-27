package com.Intern_Project.Order_Management_System.service;

import com.Intern_Project.Order_Management_System.model.OrderItem;

import java.util.List;

public interface OrderItemService {
    int createTable();
    void addOrderItem(List<OrderItem> orderItemList);

}
