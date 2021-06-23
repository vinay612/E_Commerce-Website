package com.Intern_Project.Order_Management_System.service;

import com.Intern_Project.Order_Management_System.model.Cart;

import java.util.List;

public interface CartService {
    void createTable();

    Cart insertCart(Cart cart);
    List<Cart> findByAccountId(Integer id);
    void deleteById(Integer id);
    void deleteAll(List<Cart> cartList);
    Cart updateCart(Cart cart);
}
