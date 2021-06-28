package com.Intern_Project.Order_Management_System.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Order {

    private int orderId;
    private int userId;
    private String purchaseDate;
    private String purchaseTime;
    private double totalPrice;
}
