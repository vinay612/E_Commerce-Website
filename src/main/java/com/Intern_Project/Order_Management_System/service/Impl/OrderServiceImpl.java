package com.Intern_Project.Order_Management_System.service.Impl;

import com.Intern_Project.Order_Management_System.model.OrderItem;
import com.Intern_Project.Order_Management_System.repository.OrderItemRepository;
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
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void createTable() {
        orderRepository.createTable();
    }

    @Override
    public void addOrder(Order order) {
        orderRepository.insertOrder(order);
    }

    public Order findMaxOrderIdForAccountId(Integer id){
        return orderRepository.findMaximumOrderIdForAccountId(id);
    }

    @Override
    public List<Order> getAllOrderDetails() {
        return orderRepository.findAllOrders();
    }

    @Override
    public List<OrderItem> getOrderById(int id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public List<Order> getOrderDetailsByUserId(int id) {
        return orderRepository.findOrderDetailsByUserId(id);
    }

    @Override
    public void deleteOrder(int id) {
        orderItemRepository.deleteOrderItemById(id);
        orderRepository.deleteOrder(id);
    }
}
