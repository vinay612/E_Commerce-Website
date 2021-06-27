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

    /*public Product() {
    }

    public Product(int productId, String name, double price, String description, String expiryDate, int minQuantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.expiryDate = expiryDate;
        this.minQuantity = minQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }*/
}
