package com.Intern_Project.Order_Management_System.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OrderItem {

    private int itemId;
    private int productId;
    private int quantity;
    private int orderId;
    private double price;
}
