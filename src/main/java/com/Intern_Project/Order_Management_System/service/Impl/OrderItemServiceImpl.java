package com.Intern_Project.Order_Management_System.service.Impl;

import com.Intern_Project.Order_Management_System.model.OrderItem;
import com.Intern_Project.Order_Management_System.repository.OrderItemRepository;
import com.Intern_Project.Order_Management_System.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public int createTable() {
        return orderItemRepository.createTable();
    }
    @Override
    public void addOrderItem(List<OrderItem> orderItemList) {
        orderItemRepository.addOrderItem(orderItemList);
        return ;
    }
}
