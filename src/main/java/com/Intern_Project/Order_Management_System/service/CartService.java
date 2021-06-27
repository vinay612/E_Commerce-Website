package com.Intern_Project.Order_Management_System.service;

import com.Intern_Project.Order_Management_System.model.Cart;

import java.util.List;

public interface CartService {
    void createTable();

    void insertCart(Cart cart);
    List<Cart> findByAccountId(Integer id);
    void deleteByAccountId(Integer id);
    void deleteAccountCart(List<Cart> cartList);
    void updateCart(Cart cart);
}
