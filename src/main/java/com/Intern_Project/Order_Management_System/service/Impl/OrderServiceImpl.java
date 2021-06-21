package com.Intern_Project.Order_Management_System.service.Impl;

import com.Intern_Project.Order_Management_System.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Intern_Project.Order_Management_System.model.Order;
import com.Intern_Project.Order_Management_System.repository.Impl.OrderRepositoryImpl;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepositoryImpl orderRepositoryImpl;

    @Override
    public int insertOrder(Order order) {
        return orderRepositoryImpl.insertOrder(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepositoryImpl.findAll();
    }

    @Override
    public Order findOrderById(int id) {
        return orderRepositoryImpl.findOrderById(id);
    }

    @Override
    public List<Order> findOrderDetailsByUserId(int id) {
        return orderRepositoryImpl.findOrderDetailsByUserId(id);
    }
}
