package com.Intern_Project.Order_Management_System.controller;

import com.Intern_Project.Order_Management_System.model.OrderItem;
import com.Intern_Project.Order_Management_System.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;

    @PostMapping("/orderitem")
    int insertOrder(@RequestBody OrderItem orderItem){ return orderItemService.addOrderItem(orderItem);}
}
