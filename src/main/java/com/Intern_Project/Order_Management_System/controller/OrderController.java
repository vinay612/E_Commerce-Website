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
@RequestMapping("/order")
public class OrderController {

    @Autowired
   private OrderService orderService;

    private static final String URL_ORDER_ID="{id}";
    private static final String URL_USER_ID="userId/{id}";

    @PostMapping()
    ResponseEntity<String> createOrder(@RequestBody Order order){
        orderService.addOrder(order);
        return new ResponseEntity("A new order has been placed", HttpStatus.CREATED);
    }

    @GetMapping()
    List<Order> getAllOrderDetails()
    {
        return orderService.getAllOrderDetails();
    }

    @GetMapping(value=URL_ORDER_ID)
    List<OrderItem> getOrderDetailsByOrderId(@PathVariable("id") int id){
        return orderService.getOrderById(id);
    }

    @GetMapping(value=URL_USER_ID)
    List<Order> getOrderDetailsByUserId(@PathVariable("id") int id){
        return orderService.getOrderDetailsByUserId(id);
    }

    @DeleteMapping(value=URL_ORDER_ID)
    ResponseEntity<String> deleteOrder(@PathVariable int id){
        //todo
        orderService.deleteOrder(id);
        return new ResponseEntity("Order with order id "+id+" has been cancelled.",HttpStatus.OK);
    }
}