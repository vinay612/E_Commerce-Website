package com.Intern_Project.Order_Management_System.repository;


import com.Intern_Project.Order_Management_System.model.Cart;

public interface CartRepository {

    void createTable();
    void insertCart(Integer id);
    void updateCartById(int cartId);
    Cart getCartById(Integer id);
    void deleteCartById(Integer id);
}
