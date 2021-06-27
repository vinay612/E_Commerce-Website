package com.Intern_Project.Order_Management_System.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Order {

    private int orderId;
    private int userId;
    //private int productId;
    //private int quantity;
    private String purchaseDate;
    private String purchaseTime;
    private double totalPrice;

    /*public Order() {
    }

    public Order(int orderId, int userId,String purchaseDate, String purchaseTime, double totalPrice) {
        this.orderId=orderId;
        this.userId = userId;
        //this.productId = productId;
        //this.quantity = quantity;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }*/
}
