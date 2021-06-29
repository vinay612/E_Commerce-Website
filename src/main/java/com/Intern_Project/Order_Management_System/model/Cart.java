package com.Intern_Project.Order_Management_System.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Cart {

    private int cartId;
    private int accountId;
    private int productId;
    private int quantity;
    private double totalPrice;

}
