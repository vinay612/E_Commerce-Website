package com.Intern_Project.Order_Management_System.service.Impl;

import com.Intern_Project.Order_Management_System.model.OrderItem;
import com.Intern_Project.Order_Management_System.repository.OrderRepository;
import com.Intern_Project.Order_Management_System.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Intern_Project.Order_Management_System.model.Order;
import com.Intern_Project.Order_Management_System.repository.Impl.OrderRepositoryImpl;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void createTable() {
        orderRepository.createTable();
    }

    @Override
    public int insertOrder(Order order) {
        return orderRepository.insertOrder(order);
    }

    public Order findMaxOrderIdForAccountId(Integer id){
        return orderRepository.findMaximumOrderIdForAccountId(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderItem> findOrderById(int id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public List<Order> findOrderDetailsByUserId(int id) {
        return orderRepository.findOrderDetailsByUserId(id);
    }

    @Override
    public int deleteOrder(int id) {
        return orderRepository.deleteOrder(id);
    }
}
