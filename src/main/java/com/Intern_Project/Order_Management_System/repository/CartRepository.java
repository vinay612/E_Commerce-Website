package com.Intern_Project.Order_Management_System.repository;

import com.Intern_Project.Order_Management_System.model.Cart;

import java.util.List;

public interface CartRepository {
    void createTable();
    Cart insertCart(Cart cart);
    List<Cart> findByAccountId(Integer id);
    void deleteById(Integer id);

    Cart updateCart(Cart cart);
}
