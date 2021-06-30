package com.Intern_Project.Order_Management_System.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItem {

    private int itemId;
    private int cartId;
    private int productId;
    private int quantity;
    private double totalPrice;
}
