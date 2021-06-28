package com.Intern_Project.Order_Management_System.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Product {
    private int productId;
    private String name;
    private double price;
    private String description;
    private String expiryDate;
    private int minQuantity;
}
