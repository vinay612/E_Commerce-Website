package com.Intern_Project.Order_Management_System.model;

public class OrderItem {

    private int id;
    private int productId;
    private int quantity;
    private int orderId;
    private double totalPrice;

    public OrderItem() {
    }

    public OrderItem(int id, int productId, int quantity, int orderId, double totalPrice) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.orderId = orderId;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
