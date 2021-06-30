package com.Intern_Project.Order_Management_System.service;


import com.Intern_Project.Order_Management_System.model.Cart;

public interface CartService {

    void createTable();

    void insertCart(Integer id);

    Cart getCartByCartId(Integer id);
    void updateCart(Integer id);

    void deleteCartById(Integer id);

}
