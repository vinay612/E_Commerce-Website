package com.Intern_Project.Order_Management_System.controller;

import com.Intern_Project.Order_Management_System.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Intern_Project.Order_Management_System.service.OrderService;
import com.Intern_Project.Order_Management_System.model.Order;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    private static final String URL_ADD_OR_GET_ORDER="/order";
    private static final String URL_ORDER_ID="/orderId/{id}";
    private static final String URL_USER_ID="/order/{id}";

    @PostMapping(value=URL_ADD_OR_GET_ORDER)
    ResponseEntity<String> createOrder(@RequestBody Order order){
        orderService.insertOrder(order);
        return new ResponseEntity("A new order has been placed", HttpStatus.CREATED);
    }

    @GetMapping(value=URL_ADD_OR_GET_ORDER)
    List<Order> getAll()
    {
        return orderService.findAll();
    }

    @GetMapping(value=URL_ORDER_ID)
    List<OrderItem> getOrderById(@PathVariable("id") int id){
        return orderService.findOrderById(id);
    }

    @GetMapping(value=URL_USER_ID)
    List<Order> getOrderDetailsByUserId(@PathVariable("id") int id){
        return orderService.findOrderDetailsByUserId(id);
    }

    @DeleteMapping(value=URL_ORDER_ID)
    ResponseEntity<String> deleteOrder(@PathVariable int id){

        orderService.deleteOrder(id);
        return new ResponseEntity("Order with order id "+id+" has been deleted",HttpStatus.ACCEPTED);
    }
}